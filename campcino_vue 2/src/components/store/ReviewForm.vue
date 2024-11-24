<template>
  <div class="mt-6">
    <h3 class="text-xl font-semibold mb-2">리뷰 작성</h3>
    <form @submit.prevent="submitReview" class="space-y-4">
      <div>
        <label for="rating" class="block text-sm font-medium text-gray-700">평점</label>
        <select v-model="review.rating" id="rating" required class="mt-1 block w-full border-gray-300 rounded-md shadow-sm">
          <option disabled value="">평점을 선택하세요</option>
          <option v-for="n in 5" :key="n" :value="n">{{ n }} 점</option>
        </select>
      </div>
      <div>
        <label for="comment" class="block text-sm font-medium text-gray-700">리뷰</label>
        <textarea
          v-model="review.comment"
          id="comment"
          rows="4"
          class="mt-1 block w-full border-gray-300 rounded-md shadow-sm"
          placeholder="리뷰를 작성하세요"
          required
        ></textarea>
      </div>
      <button type="submit" class="px-4 py-2 bg-blue-500 text-white rounded">리뷰 제출</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "ReviewForm",
  props: {
    productId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      review: {
        productId: this.productId,
        userId: 1, // 임시 사용자 ID (로그인 시스템 연동 필요)
        rating: "",
        comment: "",
      },
    };
  },
  methods: {
    async submitReview() {
      try {
        const response = await this.$emit('submit-review', this.review);
        if (response === 'success') {
          alert("리뷰가 성공적으로 제출되었습니다.");
          // 리뷰 폼 초기화
          this.review.rating = "";
          this.review.comment = "";
        } else {
          alert("리뷰 제출에 실패했습니다.");
        }
      } catch (error) {
        console.error("Error submitting review:", error);
        alert("리뷰 제출 중 오류가 발생했습니다.");
      }
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 가능 */
</style>
