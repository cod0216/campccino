<template>
  <Header />
  <div class="store-detail-container">
    <main class="container mx-auto p-6">
      <div v-if="product" class="flex flex-col md:flex-row">
        <img
          :src="product.productImg || 'https://via.placeholder.com/300'"
          alt="Product Image"
          class="w-full md:w-1/2 h-64 object-cover mb-4 md:mb-0"
        />
        <div class="md:ml-6">
          <h1 class="text-3xl font-bold">{{ product.productName }}</h1>
          <p class="text-xl text-gray-700 mt-2">가격: {{ product.productPrice }} 원</p>
          <p class="mt-4">{{ product.productDescription }}</p>
          <!-- 평점 표시 -->
          <div class="flex items-center mt-4">
            <i class="fas fa-star text-yellow-500 mr-2"></i>
            <span>{{ product.averageRating || '평가 없음' }}</span>
            <span class="ml-2 text-sm text-gray-600">({{ product.totalReviews || 0 }} 리뷰)</span>
          </div>
        </div>
      </div>

      <!-- 리뷰 섹션 -->
      <section class="mt-8">
        <h2 class="text-2xl font-semibold mb-4">리뷰</h2>
        <ReviewList :reviews="product.reviews" />
        <ReviewForm :productId="product.productId" @submit-review="handleSubmitReview" />
      </section>

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
            <!-- 평점 표시 -->
            <div class="flex items-center mt-2">
              <i class="fas fa-star text-yellow-500 mr-2"></i>
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
import ReviewList from "@/components/store/ReviewList.vue";
import ReviewForm from "@/components/store/ReviewForm.vue";

export default {
  name: "CampStoreDetail",
  components: { Header, ReviewList, ReviewForm },
  data() {
    return {
      product: null, // 상품 상세 데이터
      randomItems: [], // 랜덤 추천 상품 데이터
    };
  },
  methods: {
    async fetchProduct(productId) {
      try {
        const response = await axios.get(`/store/${productId}`);
        if (response.data) {
          this.product = response.data;
        } else {
          console.error("API 응답에 데이터가 없습니다.");
          this.product = null;
        }
      } catch (error) {
        console.error("Error fetching product:", error);
        this.product = null;
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
    async handleSubmitReview(review) {
      try {
        const response = await axios.post("/store/reviews", review);
        if (response.status === 200) {
          console.log("리뷰 제출 성공:", response.data);
          // 리뷰 목록 갱신을 위해 다시 상품 상세 정보 가져오기
          this.fetchProduct(this.product.productId);
          return 'success';
        } else {
          console.error("리뷰 제출 실패:", response.data);
          return 'failure';
        }
      } catch (error) {
        console.error("Error submitting review:", error);
        return 'failure';
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
    const productId = this.$route.params.id;
    if (productId) {
      this.fetchProduct(productId);
    } else {
      console.error("No productId provided in route.");
    }
    this.fetchRandomItems(); // 랜덤 추천 상품 API 호출
  },
};
</script>

<style scoped>
.store-detail-container {
  /* 스타일 추가 가능 */
}
</style>
