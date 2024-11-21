<!-- src/components/PostList.vue -->
<template>
  <div>
    <h3 class="text-[#1C160C] text-lg font-bold py-2 px-4">글 목록</h3>
    <BoardDetail v-for="post in posts" :key="post.board_id" :post="post" />
  </div>
</template>

<script>
import BoardDetail from "@/components/board/BoardDetail.vue";

export default {
  name: "BoardList",
  components: {
    BoardDetail,
  },
  props: {
    posts: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
