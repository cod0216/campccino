<template>
  <div class="login-view-container">
    <main class="container mx-auto p-6">
      <h1 class="text-4xl font-bold">로그인</h1>
      <form @submit.prevent="handleLogin" class="mt-6">
        <div class="mb-4">
          <label for="username" class="block mb-2 font-semibold">아이디</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="w-full p-2 border border-gray-300 rounded"
            required
          />
        </div>
        <div class="mb-4">
          <label for="password" class="block mb-2 font-semibold">비밀번호</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="w-full p-2 border border-gray-300 rounded"
            required
          />
        </div>
        <div class="flex gap-4">
          <button
            type="submit"
            class="w-[150px] py-2 mt-4 bg-green-600 text-white font-bold rounded"
          >
            로그인
          </button>
          <button
            type="button"
            @click="handleSignUp"
            class="w-[150px] py-2 mt-4 bg-blue-600 text-white font-bold rounded"
          >
            회원가입
          </button>
          <span class="ml-auto text-blue-500 cursor-pointer">
            <router-link to="/search">비회원으로 이용하기</router-link>
          </span>
        </div>
      </form>
    </main>
  </div>
</template>

<script>
import { useAuthStore } from "@/stores/auth"; // Pinia 상태 관리
import axios from "axios";

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
        // 로그인 요청
        // await this.authStore.login("http://localhost:8080/user/login",{
        //   userId: this.username,
        //   password: this.password,
        // });
        const response = await axios.post("http://localhost:8080/user/login", {
        userId: this.username,
        password: this.password,
      });

        

               // JWT 토큰 저장
               const { accessToken, refreshToken } = response.data;
        this.authStore.setTokens({ accessToken, refreshToken });
        alert("로그인 성공!");
        // 로그인 성공 시 메인 페이지로 이동
        this.$router.push("/");
      } catch (error) {
        console.error("로그인 실패:", error);
        alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
      }
    },
    handleSignUp() {
      // 회원가입 페이지로 이동
      this.$router.push("/join");
    },
  },
};
</script>

<style scoped>
span.ml-auto {
  margin-left: auto;
}
</style>
