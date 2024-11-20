<template>
  <div class="camp-detail-container">
    <Header />
    <main class="container mx-auto p-6">
      <!-- 로딩 상태 -->
      <div v-if="loading" class="text-center">캠핑장 정보를 로딩 중...</div>

      <!-- 오류 상태 -->
      <div v-else-if="error" class="text-center text-red-500">{{ error }}</div>

      <!-- 캠핑장 상세 정보 -->
      <div v-else class="camp-info bg-white p-6 rounded-lg shadow-lg">
        <!-- 캠핑장 이미지 갤러리 -->
        <ImageGallery :images="camp.images" />

        <!-- 캠핑장 기본 정보 -->
        <div
          class="mt-6 flex flex-col md:flex-row md:justify-between md:items-center"
        >
          <div>
            <h1 class="text-4xl font-bold text-[#1C160C]">
              {{ camp.campName }}
            </h1>
            <p class="text-sm text-[#A18249]">{{ camp.regionName }}</p>
          </div>
        </div>

        <!-- 캠핑장 설명 -->
        <p class="text-base text-[#1C160C] mt-4">{{ camp.campExplanation }}</p>

        <!-- 평점 및 리뷰 -->
        <div class="flex items-center mt-6">
          <div class="flex items-center">
            <StarRating :rating="camp.rating" size="20px" />
            <span class="ml-2 text-lg font-semibold text-[#1C160C]">{{
              camp.rating
            }}</span>
          </div>
          <span class="ml-4 text-sm text-[#A18249]"
            >{{ camp.reviews.length }} 리뷰</span
          >
        </div>

        <!-- 편의 시설 -->
        <div class="mt-6">
          <h2 class="text-2xl font-semibold text-[#1C160C]">편의 시설</h2>
          <AmenitiesList :amenities="camp.amenities" />
        </div>

        <!-- 근처 시설 -->
        <div class="mt-6">
          <h2 class="text-2xl font-semibold text-[#1C160C]">근처 시설</h2>
          <NearAmenitiesList :amenities="camp.nearAmenities" />
        </div>

        <!-- 운영 시간 -->
        <div class="mt-6">
          <h2 class="text-2xl font-semibold text-[#1C160C]">운영 시간</h2>
          <OperatingHours :operatingHours="camp.operatingHours" />
        </div>

        <!-- 글램핑 시설 (옵션) -->
        <div v-if="camp.glampingInfo" class="mt-6">
          <h2 class="text-2xl font-semibold text-[#1C160C]">글램핑 시설</h2>
          <GlampingInfo :glampingInfo="camp.glampingInfo" />
        </div>

        <!-- 캠핑장 홈페이지 링크 -->
        <div class="mt-6">
          <a
            :href="camp.campHomepage"
            target="_blank"
            class="text-blue-500 hover:underline text-lg font-medium"
          >
            캠핑장 홈페이지 방문
          </a>
        </div>

        <!-- 리뷰 섹션 -->
        <div class="mt-8">
          <h2 class="text-2xl font-semibold text-[#1C160C]">리뷰</h2>
          <Reviews :reviews="camp.reviews" />
        </div>

        <!-- 예약 섹션 -->
        <div class="mt-8 bg-[#F4EFE6] p-6 rounded-lg">
          <h2 class="text-2xl font-semibold text-[#1C160C]">예약하기</h2>
          <div class="mt-4 flex flex-col md:flex-row md:items-center md:gap-6">
            <!-- 날짜 선택 -->
            <div class="flex-1">
              <label class="block text-sm font-medium text-[#1C160C]"
                >예약 날짜</label
              >
              <Calendar @date-selected="handleDateSelected" />
            </div>
            <!-- 게스트 선택 -->
            <div class="flex-1 mt-4 md:mt-0">
              <label class="block text-sm font-medium text-[#1C160C]"
                >게스트</label
              >
              <Guests :guests="camp.guests" />
            </div>
          </div>
          <!-- 예약 버튼 -->
          <div class="mt-6">
            <ReserveButton @reserve="handleReserve" />
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import Header from "@/components/common/Header.vue";
import ImageGallery from "@/components/camp/ImageGallery.vue";
import AmenitiesList from "@/components/camp/AmenitiesList.vue";
import NearAmenitiesList from "@/components/camp/NearAmenitiesList.vue";
import OperatingHours from "@/components/camp/OperatingHours.vue";
import GlampingInfo from "@/components/camp/GlampingInfo.vue";
import Reviews from "@/components/camp/Reviews.vue";
import Calendar from "@/components/camp/Calendar.vue";
import Guests from "@/components/camp/Guests.vue";
import ReserveButton from "@/components/camp/ReserveButton.vue";
import StarRating from "@/components/camp/StarRating.vue";
import { getCampById } from "@/api";

export default {
  name: "CampDetailView",
  components: {
    Header,
    ImageGallery,
    AmenitiesList,
    NearAmenitiesList,
    OperatingHours,
    GlampingInfo,
    Reviews,
    Calendar,
    Guests,
    ReserveButton,
    StarRating,
  },
  setup() {
    const route = useRoute();
    const campId = parseInt(route.params.id);
    const camp = ref(null);
    const loading = ref(true);
    const error = ref(null);

    const fetchCampDetail = async () => {
      try {
        const data = await getCampById(campId);
        camp.value = {
          ...data,
          amenities: getAmenities(data),
          nearAmenities: getNearAmenities(data),
          operatingHours: getOperatingHours(data),
          glampingInfo: data.glampingInfo
            ? getGlampingInfo(data.glampingInfo)
            : null,
          rating: data.rating || 0,
          reviews: data.reviews || [],
          images: getImages(data),
        };
        console.log("Fetched Camp Data:", camp.value); // 디버깅용 로그
      } catch (err) {
        error.value = "캠핑장 정보를 가져오는 중 오류가 발생했습니다.";
        console.error(err);
      } finally {
        loading.value = false;
      }
    };

    const getAmenities = (data) => {
      const amenities = [];
      if (data.amenities.f_elec) amenities.push("전기");
      if (data.amenities.f_hotWater) amenities.push("온수");
      if (data.amenities.f_wifi) amenities.push("와이파이");
      if (data.amenities.f_firewood) amenities.push("장작");
      if (data.amenities.f_trails) amenities.push("산책로");
      if (data.amenities.f_swimming) amenities.push("수영장");
      if (data.amenities.f_playground) amenities.push("놀이터");
      if (data.amenities.f_market) amenities.push("시장");
      if (data.amenities.f_toilets)
        amenities.push(`화장실: ${data.amenities.f_toilets}개`);
      if (data.amenities.f_showers)
        amenities.push(`샤워장: ${data.amenities.f_showers}개`);
      if (data.amenities.f_sinks)
        amenities.push(`싱크대: ${data.amenities.f_sinks}개`);
      if (data.amenities.f_fire_extinguishers)
        amenities.push(`소화기: ${data.amenities.f_fire_extinguishers}개`);
      return amenities;
    };

    const getNearAmenities = (data) => {
      const amenities = [];
      if (data.nearAmenities.n_fishing) amenities.push("낚시터");
      if (data.nearAmenities.n_hiking) amenities.push("등산로");
      if (data.nearAmenities.n_beach) amenities.push("해변");
      if (data.nearAmenities.n_waterSports) amenities.push("수상 스포츠");
      if (data.nearAmenities.n_valley) amenities.push("계곡");
      if (data.nearAmenities.n_river) amenities.push("강");
      if (data.nearAmenities.n_pool) amenities.push("수영장");
      if (data.nearAmenities.n_youth) amenities.push("청소년 시설");
      if (data.nearAmenities.n_farm) amenities.push("농장");
      if (data.nearAmenities.n_kids) amenities.push("어린이 시설");
      return amenities;
    };

    const getOperatingHours = (data) => {
      return {
        weekday: data.operatingHours.o_weekday,
        weekend: data.operatingHours.o_weekend,
        spring: data.operatingHours.o_spring,
        summer: data.operatingHours.o_summer,
        fall: data.operatingHours.o_fall,
        winter: data.operatingHours.o_winter,
      };
    };

    const getGlampingInfo = (glampingInfoData) => {
      return {
        bed: glampingInfoData.g_bed,
        tv: glampingInfoData.g_tv,
        fridge: glampingInfoData.g_fridge,
        internet: glampingInfoData.g_internet,
        toilet: glampingInfoData.g_toilet,
        aircon: glampingInfoData.g_aircon,
        heater: glampingInfoData.g_heater,
        cookTool: glampingInfoData.g_cook_tool,
      };
    };

    const getImages = (data) => {
      // 실제 이미지 URL을 가져오는 로직을 구현하세요.
      // 여기서는 예시로 더미 이미지를 반환합니다.
      return [
        data.image1 ||
          "https://via.placeholder.com/600x400?text=Camping+Image+1",
        data.image2 ||
          "https://via.placeholder.com/600x400?text=Camping+Image+2",
        data.image3 ||
          "https://via.placeholder.com/600x400?text=Camping+Image+3",
        data.image4 ||
          "https://via.placeholder.com/600x400?text=Camping+Image+4",
        data.image5 ||
          "https://via.placeholder.com/600x400?text=Camping+Image+5",
      ];
    };

    onMounted(() => {
      fetchCampDetail();
    });

    const handleDateSelected = (date) => {
      console.log("선택된 날짜:", date);
      // 선택된 날짜를 처리하는 로직을 추가하세요.
    };

    const handleReserve = () => {
      console.log("예약 버튼 클릭됨");
      // 예약 로직을 추가하세요.
    };

    return {
      camp,
      loading,
      error,
      handleDateSelected,
      handleReserve,
    };
  },
};
</script>

<style scoped>
.camp-detail-container {
  background-color: #f9f9f9;
  min-height: 100vh;
}

.camp-info h1 {
  color: #1c160c;
}

.camp-info p {
  color: #a18249;
}

.mt-6 {
  margin-top: 1.5rem;
}

.mt-8 {
  margin-top: 2rem;
}
</style>
