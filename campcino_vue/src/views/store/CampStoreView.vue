<template>
  <Header />
  <div class="store-view-container">
    <main class="container mx-auto p-6">
      <h1 class="text-4xl font-bold">캠핑 용품 목록</h1>

      <!-- 캠핑 용품 목록 -->
      <div v-if="items && items.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mt-6">
        <div
          v-for="item in items"
          :key="item?.productId"
          class="border p-4 rounded cursor-pointer hover:shadow-lg"
          @click="goToDetail(item?.productId)"
        >
          <img
            :src="item?.productImg || 'https://via.placeholder.com/150'"
            alt="Item Image"
            class="w-full h-40 object-cover mb-4"
          />
          <h2 class="text-xl font-bold">{{ item?.productName || '정보 없음' }}</h2>
          <p class="text-lg">가격: {{ item?.productPrice || 0 }} 원</p>
          <!-- 평점 표시 -->
          <div class="flex items-center mt-2">
            <span class="text-yellow-500 mr-2">★</span>
            <span>{{ item?.averageRating || '평가 없음' }}</span>
            <span class="ml-2 text-sm text-gray-600">({{ item?.totalReviews || 0 }} 리뷰)</span>
          </div>
        </div>
      </div>

      <!-- 캠핑 용품 목록이 없을 때 -->
      <div v-else>
        <p class="text-center">캠핑 용품을 불러오는 중입니다...</p>
      </div>

      <!-- 랜덤 상품 추천 -->
      <section class="mt-12">
        <h2 class="text-3xl font-semibold mb-4">랜덤 상품 추천</h2>
        <div v-if="randomItems && randomItems.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <div
            v-for="item in randomItems"
            :key="item?.productId"
            class="border p-4 rounded cursor-pointer hover:shadow-lg"
            @click="goToDetail(item?.productId)"
          >
            <img
              :src="item?.productImg || 'https://via.placeholder.com/150'"
              alt="Item Image"
              class="w-full h-40 object-cover mb-4"
            />
            <h2 class="text-xl font-bold">{{ item?.productName || '정보 없음' }}</h2>
            <p class="text-lg">가격: {{ item?.productPrice || 0 }} 원</p>
            <!-- 평점 표시 부분 (Font Awesome 별 사용) -->
            <div class="flex items-center mt-2">
              <i class="fas fa-star text-yellow-500 mr-1"></i>
              <span>{{ item?.averageRating || '평가 없음' }}</span>
              <span class="ml-2 text-sm text-gray-600">({{ item?.totalReviews || 0 }} 리뷰)</span>
            </div>
          </div>
        </div>
        <div v-else>
          <p class="text-center">랜덤 추천 상품을 불러오는 중입니다...</p>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
import axios from "axios";
import Header from "@/components/common/Header.vue";

export default {
  name: "CampStoreView",
  components: { Header },
  data() {
    return {
      items: [], // 캠핑 용품 데이터 저장
      randomItems: [], // 랜덤 추천 상품 데이터 저장
    };
  },
  methods: {
    async fetchItems() {
      try {
        const response = await axios.get("/store/");
        if (response.data) {
          this.items = response.data;
        } else {
          console.error("API 응답에 데이터가 없습니다.");
          this.items = [];
        }
      } catch (error) {
        console.error("Error fetching items:", error);
        this.items = [];
      }
    },
    async fetchRandomItems() {
      try {
        const response = await axios.get("/store/recommend");
        if (response.data) {
          this.randomItems = response.data;
        } else {
          console.error("API 응답에 랜덤 데이터가 없습니다.");
          this.randomItems = [];
        }
      } catch (error) {
        console.error("Error fetching random items:", error);
        this.randomItems = [];
      }
    },
    goToDetail(productId) {
      if (!productId) {
        console.error("Invalid productId:", productId);
        return;
      }
      this.$router.push({ name: "CampStoreDetail", params: { id: productId } });
    },
  },
  created() {
    this.fetchItems(); // 컴포넌트 생성 시 API 호출
    this.fetchRandomItems(); // 랜덤 추천 상품 API 호출
  },
};
</script>

<style scoped>
.store-view-container {
  /* 스타일 추가 가능 */
}
</style>
