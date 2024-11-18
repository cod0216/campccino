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
        <h2 class="text-[#1C160C] text-2xl font-bold mb-4">
          글쓰기
        </h2>
        <form @submit.prevent="submitPost" class="flex flex-col gap-4">
          <div>
            <label class="block text-[#1C160C] mb-2">제목</label>
            <input
              type="text"
              v-model="newPost.board_title"
              required
              class="w-full px-4 py-2 border border-[#F4EFE6] rounded"
            />
          </div>
          <div>
            <label class="block text-[#1C160C] mb-2">내용</label>
            <textarea
              v-model="newPost.board_content"
              required
              class="w-full px-4 py-2 border border-[#F4EFE6] rounded"
              rows="6"
            ></textarea>
          </div>
          <div>
            <label class="block text-[#1C160C] mb-2">카테고리</label>
            <select
              v-model="newPost.category"
              required
              class="w-full px-4 py-2 border border-[#F4EFE6] rounded"
            >
              <option disabled value="">선택</option>
              <option
                v-for="category in categories"
                :key="category"
                :value="category"
              >
                {{ category }}
              </option>
            </select>
          </div>
          <div>
            <label class="block text-[#1C160C] mb-2">이미지 주소</label>
            <input
              type="text"
              v-model="newPost.img_url"
              placeholder="URL"
              class="w-full px-4 py-2 border border-[#F4EFE6] rounded"
            />
          </div>
          <div class="flex gap-4">
            <button
              type="submit"
              class="w-1/2 px-4 py-2 bg-[#F4EFE6] text-[#1C160C] font-bold rounded"
            >
              등록하기
            </button>
            <button
              type="button"
              @click="cancelPost"
              class="w-1/2 px-4 py-2 bg-red-400 text-white font-bold rounded"
            >
              취소
            </button>
          </div>
        </form>
      </div>
    </main>
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";
import { createBoard } from "../api";

export default {
  name: "CreatePost",
  components: {
    Header,
  },
  data() {
    return {
      headerSearch: "",
      newPost: {
        board_title: "",
        board_content: "",
        category: "",
        img_url: "",
        user_id: "user123", // 실제 사용자 ID로 대체하세요
      },
      categories: ["질문", "추천", "수다", "장비", "기타"],
    };
  },
  methods: {
    async submitPost() {
      try {
        const postData = {
          board_title: this.newPost.board_title,
          board_content: this.newPost.board_content,
          category: this.newPost.category,
          img_url: this.newPost.img_url,
          user_id: this.newPost.user_id,
          board_created_at: new Date(),
          board_view: 0,
        };
        await createBoard(postData);
        alert("새 게시글이 성공적으로 작성되었습니다!");
        this.$router.push("/board"); // /board 페이지로 이동
      } catch (error) {
        console.error("게시글 작성 중 오류가 발생했습니다:", error);
      }
    },
    cancelPost() {
      this.$router.push("/board");
    },
  },
};
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
