<!-- src/components/camp/Reviews.vue -->
<template>
  <div class="mt-4">
    <div v-for="review in reviews" :key="review.id" class="mb-6">
      <div class="flex items-center">
        <img
          :src="review.avatar"
          alt="리뷰어 아바타"
          class="w-12 h-12 rounded-full mr-4"
        />
        <div>
          <h3 class="text-lg font-semibold text-[#1C160C]">
            {{ review.reviewer }}
          </h3>
          <p class="text-sm text-[#A18249]">{{ review.date }}</p>
        </div>
      </div>
      <div class="flex items-center mt-2">
        <StarRating :rating="review.rating" size="16px" />
        <span class="ml-2 text-sm text-[#1C160C]">{{ review.rating }}</span>
      </div>
      <p class="text-base text-[#1C160C] mt-2">{{ review.comment }}</p>
    </div>
  </div>
</template>

<script>
import StarRating from "./StarRating.vue";

export default {
  name: "Reviews",
  components: {
    StarRating,
  },
  props: {
    reviews: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped></style>
