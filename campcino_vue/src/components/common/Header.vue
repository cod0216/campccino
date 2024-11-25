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

    <nav class="flex items-center gap-4">
      <router-link
        to="/search"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        캠핑장 조회
      </router-link>

      <router-link
        to="/boards"
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

      <router-link
        to="/reviews"
        class="text-[#1C160C] hover:text-[#A18249] font-medium"
      >
        리뷰
      </router-link>
    </nav>

    <div class="flex items-center gap-8">
      <!-- 로그인 상태에 따라 버튼 변경 -->
      <template v-if="isAuthenticated">
        <!-- 사용자 ID 표시 -->
        <span class="font-bold text-[#1C160C]">
          {{ user.id }}님 환영합니다!
        </span>
        <router-link to="/profile">
          <button class="px-4 py-2 bg-[#F4EFE6] text-[#1C160C] font-bold">
            회원정보
          </button>
        </router-link>
        <button
          @click="handleLogout"
          class="px-4 py-2 bg-[#F4EFE6] text-[#1C160C] font-bold"
        >
          로그아웃
        </button>
      </template>
      <template v-else>
        <router-link to="/login">
          <button class="px-4 py-2 bg-[#F4EFE6] text-[#1C160C] font-bold">
            로그인
          </button>
        </router-link>
      </template>
    </div>
  </header>
</template>

<script>
import { useAuthStore } from "@/stores/auth";

export default {
  name: "Header",
  props: {
    profileImage: {
      type: String,
      default: null, // 기본 프로필 이미지
    },
  },
  setup() {
    const authStore = useAuthStore();

    const handleLogout = () => {
      authStore.logoutUser(); // 로그아웃 호출
    };

    return {
      isAuthenticated: authStore.isAuthenticated, // 로그인 상태
      user: authStore.user, // 사용자 정보 가져오기
      handleLogout,
      defaultImage: "https://example.com/default-image.jpg", // 기본 이미지
    };
  },
};
</script>

<style scoped>
/* 기존 스타일 유지 */
</style>
