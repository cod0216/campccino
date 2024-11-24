<!-- src/components/common/Header.vue -->
<template>
  <header
    class="flex items-center justify-between px-10 py-3 border-b border-[#F4EFE6]"
  >
    <div class="flex items-center gap-8">
      <div class="w-15 h-16">
        <router-link to="/">
          <img
            src="@/assets/logo4_no.png"
            alt="logo"
            class="w-full h-full object-contain"
          />
        </router-link>
      </div>
    </div>

    <!-- 네비게이션 메뉴 수정: "리뷰" 메뉴 추가 -->
    <nav class="flex items-center gap-4">
      <router-link
        to="/search"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        캠핑장 조회
      </router-link>

      <router-link
        to="/board"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        게시판
      </router-link>

      <router-link
        to="/store"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        스토어
      </router-link>

      <!-- 새로운 "리뷰" 메뉴 추가 -->
      <router-link
        to="/reviews"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        리뷰
      </router-link>
    </nav>

    <div class="flex items-center gap-8">
      <router-link to="/login">
        <button class="px-4 py-2 bg-[#F4EFE6] text-[#1C160C] font-bold">
          로그인
        </button>
      </router-link>
      <div
        class="w-10 h-10 rounded-full bg-cover"
        :style="{ backgroundImage: `url('${profileImage}')` }"
      ></div>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  props: {
    profileImage: {
      type: String,
      default: "https://example.com/default-image.jpg",
    },
  },
};
</script>

<style scoped>
/* 기존 스타일 유지 */
</style>
