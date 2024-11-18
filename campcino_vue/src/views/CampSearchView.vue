<template>
  <div class="flex flex-col w-full min-h-screen bg-white" style="font-family: 'Noto Serif', 'Noto Sans', sans-serif">
    <Header />
    <div class="flex flex-col items-center px-10 py-6">
      <div class="w-full max-w-5xl">
        <!-- 검색 필터 섹션 -->
        <FiltersSection :regions="regions" :categories="categories" @search="handleSearch" />
        <!-- 카카오맵 섹션 -->
        <MapSection :markers="markers" ref="mapSection" />
        <!-- 캠핑장 리스트 섹션 -->
        <CampList :camps="filteredCamps" @toggle-favorite="toggleFavorite" @focus-marker="focusMarker" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import Header from "../components/common/Header.vue";
import FiltersSection from '../components/FiltersSection.vue';
import MapSection from '../components/MapSection.vue';
import CampList from '../components/CampList.vue';

const regions = ref(["전체"]); // 초기값 설정
const categories = ref(["전체"]); // 초기값 설정
const camps = ref([]);
const searchQuery = ref("");
const selectedRegion = ref("전체");
const selectedCategory = ref("전체");

const markers = ref([]);
const mapSection = ref(null);

async function fetchCamps() {
  try {
    const response = await fetch('https://api.odcloud.kr/api/15111395/v1/uddi:8c528230-eda4-4d83-855a-bee73605e49f?page=10&perPage=50&serviceKey=upfTufBMOmKlnkynpU9y4ze%2BVPuP8whZAv8lPQEzihBrIef6T1O14RBOvBznJQNBbOw51L4UCswtLzLtu43Tpw%3D%3D');
    if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

    const data = await response.json();
    camps.value = data.data.map(item => ({
      id: item['시설명'],
      name: item['시설명'],
      address: item['지번주소'],
      category: item['카테고리3'],
      latitude: Number(item['위도']),
      longitude: Number(item['경도']),
      isFavorite: false,
    }));

    // 중복 제거 후 지역 및 카테고리 설정
    const uniqueRegions = [...new Set(camps.value.map(item => item.address.split(" ")[0]))];
    const uniqueCategories = [...new Set(camps.value.flatMap(item => item.category.split(',')))];

    regions.value = ["전체", ...uniqueRegions];
    categories.value = ["전체", ...uniqueCategories];
  } catch (error) {
    console.error('데이터를 가져오는 데 오류가 발생했습니다:', error);
  }
}

const filteredCamps = computed(() => {
  return camps.value.filter((camp) => {
    const matchesRegion = selectedRegion.value === "전체" || camp.address.includes(selectedRegion.value);
    const matchesCategory = selectedCategory.value === "전체" || camp.category.includes(selectedCategory.value);
    const matchesQuery = searchQuery.value === "" || camp.name.toLowerCase().includes(searchQuery.value.toLowerCase());
    return matchesRegion && matchesCategory && matchesQuery;
  });
});

function handleSearch(region, category, query) {
  selectedRegion.value = region;
  selectedCategory.value = category;
  searchQuery.value = query;
  updateMarkers();
}

function updateMarkers() {
  markers.value = filteredCamps.value.map((camp) => ({
    title: camp.name,
    latlng: new kakao.maps.LatLng(camp.latitude, camp.longitude),
  }));
}

function toggleFavorite(campId) {
  const camp = camps.value.find((c) => c.id === campId);
  if (camp) {
    camp.isFavorite = !camp.isFavorite;
  }
}

function focusMarker(markerIndex) {
  mapSection.value.focusMarker(markerIndex);
}

onMounted(() => {
  fetchCamps();
});
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
