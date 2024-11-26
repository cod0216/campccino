<!-- src/components/store/ReviewList.vue -->
<template>
  <div>
    <div v-if="reviews && reviews.length > 0">
      <div v-for="review in reviews" :key="review.reviewId" class="border p-4 rounded mb-4">
        <div class="flex items-center">
          <i class="fas fa-star text-yellow-500 mr-2"></i>
          <span>{{ review.shopRate }}</span>
          <span class="ml-2 text-sm text-gray-600">{{ review.createdAt }}</span>
        </div>
        <p class="mt-2">{{ review.comment || '리뷰 내용 없음' }}</p>
      </div>
    </div>
    <div v-else>
      <p>리뷰가 없습니다.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "ReviewList",
  props: {
    reviews: {
      type: Array,
      required: true
    }
  }
};
</script>

<style scoped>
/* 스타일 추가 가능 */
</style>
