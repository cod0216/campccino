<template>
  <div class="login-view-container">
    <main class="container mx-auto p-6">
      <h1 class="text-4xl font-bold mb-6">로그인</h1>
      <form @submit.prevent="handleLogin" class="space-y-4">
        <div>
          <label for="username" class="block font-semibold">아이디</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="w-full p-3 border border-gray-300 rounded"
            placeholder="아이디를 입력하세요"
            required
          />
        </div>
        <div>
          <label for="password" class="block font-semibold">비밀번호</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="w-full p-3 border border-gray-300 rounded"
            placeholder="비밀번호를 입력하세요"
            required
          />
        </div>
        <div class="flex items-center gap-4">
          <button
            type="submit"
            class="w-[150px] py-3 bg-green-600 text-white font-bold rounded hover:bg-green-700"
          >
            로그인
          </button>
          <router-link to="/join" class="ml-auto text-blue-500 hover:underline">
            회원가입
          </router-link>
        </div>
      </form>
    </main>
  </div>
</template>

<script>
import { useAuthStore } from "@/stores/auth";

export default {
  name: "LoginView",
  setup() {
    const authStore = useAuthStore();
    return { authStore };
  },
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async handleLogin() {
      try {
        await this.authStore.loginUser({
          userId: this.username,
          password: this.password,
        });
        alert("로그인 성공!");
        this.$router.push("/");
      } catch (error) {
        console.error("로그인 실패:", error);
        alert("로그인에 실패했습니다. 다시 시도해주세요.");
      }
    },
    handleSignUp() {
      this.$router.push("/join");
    },
  },
};
</script>
