<template>
  <div
    class="flex flex-col w-full min-h-screen bg-white"
    style="font-family: 'Noto Serif', 'Noto Sans', sans-serif"
  >
    <Header />
    <div class="flex flex-col items-center px-10 py-6">
      <div class="w-full max-w-5xl">
        <FiltersSection
          :regions="regions"
          :categories="categories"
          @search="handleSearch"
        />
        <MapSection :markers="markers" ref="mapSection" />
        <div v-if="loading">캠핑장 로딩 중...</div>
        <div v-else-if="error">
          <p class="text-red-500">{{ error }}</p>
        </div>
        <div v-else>
          <CampList :camps="camps" @focus-marker="focusMarker" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getCamps, getRegions, getCategories } from "@/api";
import Header from "@/components/common/Header.vue";
import FiltersSection from "@/components/camp/FiltersSection.vue";
import MapSection from "@/components/camp/MapSection.vue";
import CampList from "@/components/camp/CampList.vue";

const regions = ref([]);
const categories = ref([]);
const camps = ref([]);
const markers = ref([]);
const mapSection = ref(null);

const loading = ref(false);
const error = ref(null);

// 캠프장 카테고리와 그에 해당하는 ID 매핑
const categoryMappings = {
  글램핑: [1, 2, 3, 7, 8, 10, 14, 15, 18],
  일반야영장: [2, 3, 4, 5, 8, 10, 11, 12, 16, 17],
  자동차야영장: [2, 5, 7, 9, 10, 12, 15, 16],
  카라반: [6, 11, 12, 13, 14, 15, 17, 18],
};

// 캠프장 데이터를 가져오는 함수
const fetchCamps = async (
  regionId = 0,
  selectedCategories = [],
  query = ""
) => {
  loading.value = true;
  error.value = null;
  try {
    const campData = await getCamps(regionId, selectedCategories, query);
    camps.value = campData.map((item) => ({
      id: item.campId,
      name: item.campName,
      address: item.roadAddress,
      latitude: item.latitude,
      longitude: item.longitude,
    }));
    updateMarkers();
  } catch (err) {
    error.value = "캠핑장 데이터를 가져오는 중 오류가 발생했습니다.";
  } finally {
    loading.value = false;
  }
};

// 필터 데이터를 가져오는 함수
const fetchFilters = async () => {
  try {
    const [regionsData, categoriesData] = await Promise.all([
      getRegions(),
      getCategories(),
    ]);
    regions.value = regionsData;
    categories.value = categoriesData;
  } catch (err) {
    error.value = "필터 데이터를 가져오는 중 오류가 발생했습니다.";
  }
};

// 초기 데이터를 가져오는 함수
const fetchData = async () => {
  await fetchFilters();
};

// 검색 버튼 클릭 시 필터링된 캠프장 데이터 가져오기
const handleSearch = (regionId, selectedCategory, query) => {
  const categoryIdsToSearch =
    selectedCategory === "0"
      ? [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]
      : categoryMappings[selectedCategory] || [];

  fetchCamps(regionId, categoryIdsToSearch, query);
};

// 마커 업데이트 함수
const updateMarkers = () => {
  markers.value = camps.value.map((camp) => ({
    id: camp.id,
    title: camp.name,
    latlng: new kakao.maps.LatLng(camp.latitude, camp.longitude),
  }));
};

// 캠프장 마커에 포커스하는 함수
const focusMarker = (campId) => {
  const campIndex = camps.value.findIndex((camp) => camp.id === campId);
  if (campIndex !== -1 && mapSection.value) {
    mapSection.value.focusMarker(campIndex);
  }
};

// 컴포넌트가 마운트될 때 필터 및 캠프장 데이터 가져오기
onMounted(() => {
  fetchData();
});
</script>

<style scoped>
/* 필요한 스타일을 추가하세요 */
</style>
