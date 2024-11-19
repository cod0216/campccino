<!-- src/views/CampSearchView.vue -->
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

const fetchCamps = async (regionId = 0, categoryId = 0, query = "") => {
  loading.value = true;
  error.value = null;
  try {
    const campData = await getCamps(regionId, categoryId, query);
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

const fetchData = async () => {
  await fetchFilters();
};

const handleSearch = (regionId, categoryId, query) => {
  fetchCamps(regionId, categoryId, query);
};

const updateMarkers = () => {
  markers.value = camps.value.map((camp) => ({
    id: camp.id,
    title: camp.name,
    latlng: new kakao.maps.LatLng(camp.latitude, camp.longitude),
  }));
};

const focusMarker = (campId) => {
  const campIndex = camps.value.findIndex((camp) => camp.id === campId);
  if (campIndex !== -1 && mapSection.value) {
    mapSection.value.focusMarker(campIndex);
  }
};

onMounted(() => {
  fetchData();
});
</script>

<style scoped>
/* 필요한 스타일을 추가하세요 */
</style>
