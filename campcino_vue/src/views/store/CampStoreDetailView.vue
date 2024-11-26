<!-- src/views/StoreDetailView.vue -->
<template>
  <div>
    <Header />
    <div v-if="storeDetail" class="p-6">
      <h1 class="text-2xl font-bold">{{ storeDetail.shopTitle }}</h1>
      <img
        :src="storeDetail.shopImg"
        :alt="storeDetail.shopTitle"
        @error="handleImageError"
        class="w-full h-64 object-cover rounded mt-4"
      />
      <p class="text-lg mt-4">
        Price: ₩{{ storeDetail.shopPrice.toLocaleString() }}
      </p>
      <p class="text-yellow-500">
        ⭐ {{ formattedRating }}
      </p>
      <p class="mt-2">{{ storeDetail.shopComment }}</p>

      <!-- 리뷰 섹션 -->
      <div class="mt-6">
        <h2 class="text-xl font-semibold mb-4">Reviews</h2>
        <ReviewList :reviews="storeReviews" />
        <ReviewForm :storeId="storeId" @submit-review="submitReview" />
      </div>
    </div>
    <div v-else class="p-6">
      <p>스토어 상세 정보를 로드 중입니다...</p>
    </div>
  </div>
</template>

<script>
import { useStoreStore } from "@/stores/storeStore";
import { onMounted, computed } from "vue";
import Header from "@/components/common/Header.vue";
import ReviewList from "@/components/store/ReviewList.vue";
import ReviewForm from "@/components/store/ReviewForm.vue"; // 리뷰 폼 컴포넌트 임포트
import { useRoute } from "vue-router";

export default {
  name: "StoreDetailView",
  components: { Header, ReviewList, ReviewForm },
  setup() {
    const storeStore = useStoreStore();
    const route = useRoute();
    const storeId = parseInt(route.params.id, 10);

    onMounted(async () => {
      try {
        await storeStore.fetchStoreDetail(storeId);
        console.log('스토어 상세 정보:', storeStore.getStoreDetail(storeId)); // 디버깅용
        await storeStore.fetchStoreReviews(storeId);
      } catch (error) {
        console.error("스토어 상세 정보 또는 리뷰를 가져오는 중 오류 발생:", error);
        alert("스토어 상세 정보를 로드하지 못했습니다. 나중에 다시 시도해주세요.");
      }
    });

    const submitReview = async (review) => {
      try {
        await storeStore.submitShopReview(storeId, review);
        alert("리뷰가 성공적으로 제출되었습니다.");
      } catch (error) {
        console.error("리뷰 제출 실패:", error);
        alert("리뷰 제출에 실패했습니다. 나중에 다시 시도해주세요.");
      }
    };

    const handleImageError = (event) => {
      event.target.src = "https://via.placeholder.com/400x300?text=Image+Not+Available"; // 대체 이미지 URL
    };

    // 안전한 접근을 위한 계산된 속성
    const formattedRating = computed(() => {
      const rating = storeStore.getStoreDetail(storeId)?.rating;
      return rating !== undefined ? rating.toFixed(2) : 'No rating';
    });

    const storeDetail = computed(() => storeStore.getStoreDetail(storeId));
    const storeReviews = computed(() => storeStore.getStoreReviews(storeId));

    return {
      storeDetail,
      storeReviews,
      submitReview,
      handleImageError,
      formattedRating,
      storeId,
    };
  },
};
</script>

<style scoped>
/* 필요한 경우 추가 스타일링 */
</style>
