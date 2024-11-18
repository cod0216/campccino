<!-- src/views/Home.vue -->
<template>
  <div
    class="relative flex w-full min-h-screen flex-col bg-white overflow-x-hidden"
    style="font-family: 'Be Vietnam Pro', 'Noto Sans', sans-serif"
  >
    <Header />

    <!-- Main Content -->
    <main class="flex flex-1 justify-center py-5 px-6">
      <div class="max-w-[920px] w-full flex flex-col">
        <!-- Bulletin Board Title -->
        <div class="flex justify-between gap-3 p-4">
          <p class="text-[#1C160C] text-2xl font-bold">캠핑 게시판</p>
        </div>

        <!-- Search for a Topic -->
        <SearchBar v-model="topicSearch" placeholder="검색어를 입력하세요." />

        <!-- Category Tags -->
        <CategoryTags
          :categories="categories"
          :selectedCategory="selectedCategory"
          @update:selectedCategory="selectedCategory = $event"
        />

        <!-- Popular Topics -->
        <PostList :posts="filteredPosts" />

        <!-- Start New Post Button -->
        <StartNewPostButton />
      </div>
    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import SearchBar from "@/components/board/SearchBar.vue";
import CategoryTags from "@/components/board/CategoryTags.vue";
import PostList from "@/components/post/PostList.vue";
import StartNewPostButton from "@/components/board/StartNewPostButton.vue";
import { getBoards } from "../../api";

export default {
  name: "Home",
  components: {
    Header,
    SearchBar,
    CategoryTags,
    PostList,
    StartNewPostButton,
  },
  data() {
    return {
      headerSearch: "",
      topicSearch: "",
      selectedCategory: "All",
      categories: ["전체", "질문", "추천", "수다", "장비", "기타"],
      posts: [],
    };
  },
  computed: {
    filteredPosts() {
      return this.posts.filter((post) => {
        const matchesCategory =
          this.selectedCategory === "All" ||
          post.category === this.selectedCategory;
        const matchesSearch =
          post.board_title
            .toLowerCase()
            .includes(this.topicSearch.toLowerCase()) ||
          post.board_content
            .toLowerCase()
            .includes(this.topicSearch.toLowerCase());
        return matchesCategory && matchesSearch;
      });
    },
  },
  created() {
    this.fetchPosts();
  },
  methods: {
    async fetchPosts() {
      try {
        const response = await getBoards();
        this.posts = response.data;
      } catch (error) {
        console.error("게시글을 가져오는 중 오류가 발생했습니다:", error);
      }
    },
  },
};
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
