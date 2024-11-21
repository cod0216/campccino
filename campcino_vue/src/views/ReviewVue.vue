<template>
  <div class="main-view-container">
    <Header />
    <main class="container mx-auto p-6">
      <h1 class="text-4xl font-bold">캠핑장 후기 게시판</h1>
      <PostList :posts="posts" />
    </main>
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import PostList from "../components/board/BoardList.vue";

export default {
  name: "MainView",
  components: {
    Header,
    PostList,
  },
  data() {
    return {
      posts: [
        {
          id: 1,
          title: "서울 근교 캠핑장 후기",
          content: "좋은 캠핑장이었습니다.",
          comments: 5,
        },
        {
          id: 2,
          title: "강원도 자연 캠핑 후기",
          content: "자연 속에서 편히 쉬다 왔어요.",
          comments: 8,
        },
      ],
    };
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
