<template>
  <div class="login-view-container">
    <Header />
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
            class="w-full p-2 border border-gray-300 rounded"
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
        </div>
      </form>
    </main>
  </div>
</template>

<script>
import Header from "../components/common/Header.vue";

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
    handleLogin() {
      // 로그인 처리 로직 구현
      console.log(`아이디: ${this.username}, 비밀번호: ${this.password}`);
    },
    handleSignUp() {
      // 회원가입 페이지로 이동 로직 구현
      this.$router.push("/join");
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
