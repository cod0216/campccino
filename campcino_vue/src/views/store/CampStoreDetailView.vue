<!-- src/views/StoreDetailView.vue -->
<template>
  <div>
    <Header />
    <div v-if="storeDetail" class="p-6">
      <h1 class="text-2xl font-bold">{{ storeDetail.shopTitle }}</h1>
      <img
        :src="storeDetail.image"
        :alt="storeDetail.shopTitle"
        @error="handleImageError"
        class="w-full h-64 object-cover rounded mt-4"
      />
      <p class="text-lg mt-4">
        Price: ${{ formattedPrice }}
      </p>
      <p class="text-yellow-500">
        ⭐ {{ formattedRating }}
      </p>
      <p class="mt-2">{{ storeDetail.shopComment }}</p>

      <!-- 리뷰 섹션 -->
      <div class="mt-6">
        <h2 class="text-xl font-semibold mb-4">Reviews</h2>
        <div v-if="storeReviews.length > 0">
          <div v-for="review in storeReviews" :key="review.reviewId" class="mb-4 border-b pb-2">
            <p class="text-yellow-500">⭐ {{ review.shopRate }}</p>
            <p>{{ review.comment }}</p>
            <p class="text-gray-500 text-sm">{{ review.userId }} - {{ review.createdAt }}</p>
          </div>
        </div>
        <div v-else>
          <p class="text-gray-500">No reviews yet.</p>
        </div>

        <!-- 리뷰 작성 폼 -->
        <div class="mt-6">
          <h3 class="text-lg font-semibold mb-2">Add a Review</h3>
          <form @submit.prevent="submitReview">
            <div class="mb-4">
              <label for="shopRate" class="block text-gray-700">Rating</label>
              <select v-model.number="newReview.shopRate" id="shopRate" required class="mt-1 block w-full border rounded p-2">
                <option disabled value="">Select rating</option>
                <option v-for="n in 5" :key="n" :value="n">{{ n }}</option>
              </select>
            </div>
            <div class="mb-4">
              <label for="comment" class="block text-gray-700">Comment</label>
              <textarea v-model="newReview.comment" id="comment" rows="3" required class="mt-1 block w-full border rounded p-2"></textarea>
            </div>
            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Submit Review</button>
          </form>
        </div>
      </div>
    </div>
    <div v-else class="p-6">
      <p>스토어 상세 정보를 로드 중입니다...</p>
    </div>
  </div>
</template>

<script>
import { useStoreStore } from "@/stores/storeStore";
import { onMounted, reactive, computed } from "vue";
import Header from "@/components/common/Header.vue";
import { useRoute } from "vue-router";

export default {
  name: "StoreDetailView",
  components: { Header },
  setup() {
    const storeStore = useStoreStore();
    const route = useRoute();
    const storeId = parseInt(route.params.id, 10);

    const newReview = reactive({
      userId: "currentUserId", // 실제 사용자 ID로 교체
      shopRate: null,
      comment: "",
    });

    onMounted(async () => {
      try {
        await storeStore.fetchStoreDetail(storeId);
        console.log('스토어 상세 정보:', storeStore.storeDetail); // 디버깅용
        await storeStore.fetchStoreReviews(storeId);
      } catch (error) {
        console.error("스토어 상세 정보 또는 리뷰를 가져오는 중 오류 발생:", error);
        alert("스토어 상세 정보를 로드하지 못했습니다. 나중에 다시 시도해주세요.");
      }
    });

    const submitReview = async () => {
      try {
        await storeStore.submitShopReview(storeId, {
          userId: newReview.userId,
          shopRate: newReview.shopRate,
          comment: newReview.comment
        });
        // 폼 초기화
        newReview.shopRate = null;
        newReview.comment = "";
      } catch (error) {
        console.error("리뷰 제출 실패:", error);
        alert("리뷰 제출에 실패했습니다. 나중에 다시 시도해주세요.");
      }
    };

    const handleImageError = (event) => {
      event.target.src = "https://via.placeholder.com/400x300?text=Image+Not+Available"; // 대체 이미지 URL
    };

    // 안전한 접근을 위한 계산된 속성
    const formattedPrice = computed(() => {
      return storeStore.storeDetail.shopPrice !== undefined
        ? storeStore.storeDetail.shopPrice.toFixed(2)
        : 'N/A';
    });

    const formattedRating = computed(() => {


      return storeStore.storeDetail.rating !== undefined
        ? storeStore.storeDetail.rating.toFixed(2)
        : 'No rating';
    });

    return {
      storeDetail: storeStore.storeDetail,
      storeReviews: storeStore.storeReviews,
      newReview,
      submitReview,
      handleImageError,
      formattedPrice,
      formattedRating,
    };
  },
};
</script>

<style scoped>
/* 필요한 경우 추가 스타일링 */
</style>
