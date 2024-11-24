<template>
  <div class="p-4 border border-solid border-[#F4EFE6] rounded-lg">
    <div class="flex gap-3 items-center mb-2">
      <span class="text-[#1C160C] font-semibold">{{ review.username }}</span>
      <span class="text-[#A18249] text-sm">{{ review.date }}</span>
      <div class="flex gap-0.5 ml-auto">
        <span v-for="star in review.rating" :key="star" class="text-[#019863]">
          <svg xmlns="http://www.w3.org/2000/svg" width="20px" height="20px" fill="currentColor" viewBox="0 0 256 256">
            <path d="M234.5,114.38l-45.1,39.36,13.51,58.6a16,16,0,0,1-23.84,17.34l-51.11-31-51,31a16,16,0,0,1-23.84-17.34L66.61,153.8,21.5,114.38a16,16,0,0,1,9.11-28.06l59.46-5.15,23.21-55.36a15.95,15.95,0,0,1,29.44,0h0L166,81.17l59.44,5.15a16,16,0,0,1,9.11,28.06Z"></path>
          </svg>
        </span>
      </div>
    </div>
    <p class="text-[#1C160C]">{{ review.comment }}</p>
  </div>
</template>

<script>
export default {
  name: "ReviewItem",
  props: {
    review: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
