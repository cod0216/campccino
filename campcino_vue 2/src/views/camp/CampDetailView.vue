<!-- src/components/camp/CampDetailView.vue -->
<template>
  <div class="camp-detail-container">
    <!-- <Header :profileImage="profileImage" /> -->
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
            <h1
              class="text-4xl font-bold text-primary md:mb-6 flex items-center"
            >
              {{ camp.campName }}
              <!-- 운영 상태 아이콘 추가 -->
              <span class="ml-2">
                <CheckCircleIcon
                  v-if="isOperatingNow"
                  class="h-6 w-6 text-green-500"
                />
                <XCircleIcon v-else class="h-6 w-6 text-red-500" />
              </span>
            </h1>
            <p class="text-2xl text-secondary">{{ camp.roadAddress }}</p>
          </div>
        </div>

        <!-- 캠핑장 설명 -->
        <p class="text-base text-primary mt-4">{{ camp.campExplanation }}</p>

        <!-- 섹션들을 Grid로 수평 정렬 -->
        <div class="mt-6 grid grid-cols-1 md:grid-cols-4 gap-6">
          <!-- 편의 시설 -->
          <div>
            <h2 class="text-2xl font-semibold text-primary">편의 시설</h2>
            <AmenitiesList :amenities="camp.amenities" />
          </div>

          <!-- 근처 시설 -->
          <div>
            <h2 class="text-2xl font-semibold text-primary">근처 시설</h2>
            <NearAmenitiesList
              v-if="camp.nearAmenities && camp.nearAmenities.length > 0"
              :amenities="camp.nearAmenities"
            />
            <div v-else class="text-sm text-secondary">없음</div>
          </div>

          <!-- 운영 시간 -->
          <div>
            <h2 class="text-2xl font-semibold text-primary">운영 시간</h2>
            <OperatingHours :operatingHours="camp.operatingHours" />
          </div>

          <!-- 글램핑 시설 (옵션) -->
          <div v-if="camp.glampingInfo">
            <h2 class="text-2xl font-semibold text-primary">글램핑 시설</h2>
            <GlampingInfo :glampingInfo="camp.glampingInfo" />
          </div>
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
          <!-- 리뷰 헤더와 평점 및 리뷰 수를 수평으로 정렬 -->
          <div class="flex flex-col md:flex-row md:justify md:items-center">
            <h2 class="text-2xl font-semibold text-primary md:mr-3">리뷰</h2>
            <div class="flex items-center mt-2 md:mt-0">
              <StarRating :rating="camp.rating" size="25px" />
              <span class="ml-2 text-lg font-semibold text-primary">
                {{ camp.rating }}
              </span>
              <span class="ml-4 text-sm text-secondary">
                {{ totalReviewCount }} 리뷰
              </span>
            </div>
          </div>

          <!-- 리뷰 리스트 -->
          <Reviews :reviews="reviews" />

          <!-- 리뷰 페이지네이션 -->
          <div class="flex justify-center mt-4">
            <button
              @click="goToReviewPage(currentReviewPage - 1)"
              :disabled="currentReviewPage === 1"
              class="px-3 py-1 mx-1 bg-gray-200 rounded disabled:opacity-50"
            >
              이전
            </button>
            <button
              v-for="page in visibleReviewPages"
              :key="page"
              @click="goToReviewPage(page)"
              :class="[
                'px-3 py-1 mx-1 rounded',
                {
                  'bg-blue-500 text-white': page === currentReviewPage,
                  'bg-gray-200': page !== currentReviewPage,
                },
              ]"
            >
              {{ page }}
            </button>
            <button
              @click="goToReviewPage(currentReviewPage + 1)"
              :disabled="currentReviewPage === totalReviewPages"
              class="px-3 py-1 mx-1 bg-gray-200 rounded disabled:opacity-50"
            >
              다음
            </button>
          </div>
        </div>

        <!-- 예약 섹션 -->
        <div class="mt-8 bg-[#F4EFE6] p-6 rounded-lg">
          <h2 class="text-2xl font-semibold text-primary">예약하기</h2>
          <div class="mt-4 flex flex-col md:flex-row md:items-center md:gap-6">
            <!-- 날짜 선택 -->
            <div class="flex-1">
              <label class="block text-sm font-medium text-primary">
                예약 날짜
              </label>
              <Calendar @date-selected="handleDateSelected" />
            </div>
            <!-- 게스트 선택 -->
            <div class="flex-1 mt-4 md:mt-0">
              <label class="block text-sm font-medium text-primary">
                게스트
              </label>
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
// Heroicons v2에서 Solid 아이콘 임포트
import { CheckCircleIcon, XCircleIcon } from "@heroicons/vue/24/solid";
import { ref, onMounted, computed } from "vue";
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
import { getCampById, getPaginatedReviewsByCampId } from "@/api";

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
    CheckCircleIcon,
    XCircleIcon,
  },
  props: {
    campId: {
      type: Number,
      required: true,
    },
  },
  setup(props) {
    const camp = ref(null);
    const loading = ref(true);
    const error = ref(null);

    // 리뷰 페이징 상태
    const reviews = ref([]);
    const currentReviewPage = ref(1);
    const totalReviewPages = ref(1);
    const totalReviewCount = ref(0);
    const reviewPageSize = 5;

    // 사용자 프로필 이미지 (예시)
    const profileImage = ref("https://example.com/default-image.jpg");

    const fetchCampDetail = async () => {
      try {
        const data = await getCampById(props.campId);
        console.log("Raw API Data:", data); // 원본 데이터 확인

        camp.value = {
          ...data,
          amenities: getAmenities(data),
          nearAmenities: getNearAmenities(data),
          operatingHours: getOperatingHours(data),
          glampingInfo: data.glampingInfo
            ? getGlampingInfo(data.glampingInfo)
            : null,
          rating: calculateAverageRating(data.reviews),
          images: getImages(data),
          // 리뷰는 별도로 페이징 처리할 것이므로 제외
        };

        // 총 리뷰 수 설정
        totalReviewCount.value = data.reviewCount || data.reviews.length;

        console.log("Processed Camp Data:", camp.value); // 처리된 데이터 확인
        console.log("Calculated Rating:", camp.value.rating); // 계산된 평점 확인
      } catch (err) {
        error.value = "캠핑장 정보를 가져오는 중 오류가 발생했습니다.";
        console.error(err);
      } finally {
        loading.value = false;
      }
    };

    const fetchReviews = async () => {
      try {
        const data = await getPaginatedReviewsByCampId(
          props.campId,
          currentReviewPage.value,
          reviewPageSize
        );
        reviews.value = data.items;
        currentReviewPage.value = data.currentPage;
        totalReviewPages.value = data.totalPages;
        totalReviewCount.value = data.totalItems;
      } catch (error) {
        console.error("리뷰를 가져오는 중 오류 발생:", error);
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
        spring: data.operatingHours.o_spring,
        summer: data.operatingHours.o_summer,
        fall: data.operatingHours.o_fall,
        winter: data.operatingHours.o_winter,
        weekday: data.operatingHours.o_weekday,
        weekend: data.operatingHours.o_weekend,
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

    /**
     * 평균 평점을 계산하는 함수
     * @param {Array} reviews - 리뷰 배열
     * @returns {Number} - 평균 평점 (소수점 첫째 자리까지)
     */
    const calculateAverageRating = (reviews) => {
      if (!reviews || reviews.length === 0) return 0;
      let total = 0;
      let validCount = 0;

      reviews.forEach((review) => {
        // 'campRate' 필드 이름을 확인하세요 (백엔드 응답에 따라 변경)
        const rate = Number(review.campRate); // 'camp_rate'에서 'campRate'로 변경
        console.log(
          `Review ID: ${review.review_id}, campRate: ${review.campRate}, Converted Rate: ${rate}`
        );
        if (!isNaN(rate) && rate >= 0 && rate <= 5) {
          total += rate;
          validCount += 1;
        } else {
          console.warn(
            `Invalid camp_rate found: ${review.campRate} in review ID ${review.review_id}`
          );
        }
      });

      if (validCount === 0) return 0;

      const avg = total / validCount;
      console.log(
        `Total valid ratings: ${total}, Count: ${validCount}, Average: ${avg}`
      );
      return parseFloat(avg.toFixed(1));
    };

    /**
     * 현재 계절을 반환하는 함수
     * @returns {String} - 'spring', 'summer', 'fall', 'winter'
     */
    const getCurrentSeason = () => {
      const month = new Date().getMonth() + 1; // 0-based index
      if (month >= 3 && month <= 5) return "spring";
      if (month >= 6 && month <= 8) return "summer";
      if (month >= 9 && month <= 11) return "fall";
      return "winter";
    };

    /**
     * 현재 계절에 따른 운영 상태를 계산하는 함수
     * @returns {Boolean} - 운영 중이면 true, 아니면 false
     */
    const isOperatingNow = computed(() => {
      if (!camp.value || !camp.value.operatingHours) return false;
      const currentSeason = getCurrentSeason();
      const seasonHours = camp.value.operatingHours[currentSeason];
      // seasonHours이 true/false 또는 운영 시간을 나타내는 값에 따라 조건을 조정하세요.
      // 예시: 'o_spring': true 또는 false
      return seasonHours === true;
    });

    onMounted(() => {
      fetchCampDetail();
      fetchReviews();
    });

    const handleDateSelected = (date) => {
      console.log("선택된 날짜:", date);
      // 선택된 날짜를 처리하는 로직을 추가하세요.
    };

    const handleReserve = () => {
      console.log("예약 버튼 클릭됨");
      // 예약 로직을 추가하세요.
    };

    // 페이지네이션 계산
    const visibleReviewPages = computed(() => {
      const pages = [];
      const maxVisible = 5;
      let start = Math.max(
        currentReviewPage.value - Math.floor(maxVisible / 2),
        1
      );
      let end = start + maxVisible - 1;

      if (end > totalReviewPages.value) {
        end = totalReviewPages.value;
        start = Math.max(end - maxVisible + 1, 1);
      }

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
      return pages;
    });

    const goToReviewPage = (page) => {
      if (page < 1 || page > totalReviewPages.value) return;
      currentReviewPage.value = page;
      fetchReviews();
    };

    return {
      camp,
      loading,
      error,
      reviews,
      currentReviewPage,
      totalReviewPages,
      totalReviewCount,
      visibleReviewPages,
      goToReviewPage,
      handleDateSelected,
      handleReserve,
      profileImage, // 프로필 이미지 전달
      isOperatingNow, // 운영 상태
      CheckCircleIcon,
      XCircleIcon,
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

/* 추가적인 스타일링이 필요하다면 여기에 작성하세요 */
</style>
