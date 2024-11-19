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
            class="w-full p-2 border border-gray-300 rounded" required
          />
        </div>
        <div class="mb-4">
          <label for="password" class="block mb-2 font-semibold"
            >비밀번호</label
          >
          <input
            type="password"
            id="password"
            v-model="password"
            class="w-full p-2 border border-gray-300 rounded" required
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
        <router-link
        to="/search">
        비회원으로 이용하기
    </router-link>
  </span>
        </div>
      </form>

    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import axios from 'axios';

export default {
  name: "LoginView",
  components: {
    Header,
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
        const response = await axios.post("/api/auth/login", {
          username: this.username,
          password: this.password,
        });
        const token = response.data.token;
        // JWT 토큰 저장
        localStorage.setItem("token", token);
        alert("로그인 성공!");
        // 로그인 후 메인 페이지로 이동
        this.$router.push("/");
      } catch (error) {
        console.error(error);
        alert("로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
      }
    },
    handleSignUp() {
      // 회원가입 페이지로 이동 로직 구현
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

