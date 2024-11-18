<!-- src/views/PostDetail.vue -->
<template>
  <div
    class="relative flex w-full min-h-screen flex-col bg-white overflow-x-hidden"
    style="font-family: 'Be Vietnam Pro', 'Noto Sans', sans-serif"
  >
    <Header
      :headerSearch="headerSearch"
      @update:headerSearch="headerSearch = $event"
    />
    <main class="flex flex-1 justify-center py-5 px-6">
      <div class="max-w-[920px] w-full flex flex-col">
        <div v-if="post" class="flex flex-col gap-4">
          <h2 class="text-[#1C160C] text-2xl font-bold">
            {{ post.board_title }}
          </h2>
          <p class="text-[#A18249] text-sm">{{ formattedDate }}</p>
          <div class="flex items-center gap-4">
            <img
              v-if="post.img_url"
              :src="post.img_url"
              alt="Post Image"
              class="w-32 h-32 object-cover rounded"
            />
            <p class="text-[#1C160C]">{{ post.board_content }}</p>
          </div>
          <p class="text-[#1C160C]">Views: {{ post.board_view }}</p>
          <p class="text-[#1C160C]">User ID: {{ post.user_id }}</p>
        </div>
        <div v-else>
          <p>Loading...</p>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import { getBoardById, updateBoard } from "../api"; // 올바른 경로 확인

export default {
  name: "PostDetail",
  components: {
    Header,
  },
  props: {
    id: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      headerSearch: "",
      post: null,
    };
  },
  computed: {
    formattedDate() {
      if (this.post && this.post.board_created_at) {
        return new Date(this.post.board_created_at).toLocaleString();
      }
      return "";
    },
  },
  created() {
    this.fetchPost();
  },
  methods: {
    async fetchPost() {
      try {
        const response = await getBoardById(this.id);
        this.post = response.data;
        this.incrementView();
      } catch (error) {
        console.error("게시글을 가져오는 중 오류가 발생했습니다:", error);
      }
    },
    async incrementView() {
      try {
        await updateBoard(this.id, { board_view: this.post.board_view + 1 });
        this.post.board_view += 1;
      } catch (error) {
        console.error("조회수를 증가시키는 중 오류가 발생했습니다:", error);
      }
    },
  },
};
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
