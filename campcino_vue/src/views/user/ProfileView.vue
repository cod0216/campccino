<template>
  <div
    class="relative flex size-full min-h-screen flex-col bg-[#FFFFFF] group/design-root overflow-x-hidden"
  >
    <HeaderComponent />
    <main class="px-40 flex flex-1 justify-center py-5">
      <div class="layout-content-container flex flex-col max-w-[960px] flex-1">
        <section class="flex flex-wrap justify-between gap-3 p-4">
          <div class="flex min-w-72 flex-col gap-3">
            <p
              class="text-[#1C160C] text-4xl font-black leading-tight tracking-[-0.033em]"
            >
              회원정보 수정
            </p>
            <p class="text-[#A18249] text-base font-normal leading-normal">
              변경하고 싶은 정보를 업데이트해주세요.
            </p>
          </div>
        </section>
        <section class="flex flex-col items-center gap-4 p-4"></section>
        <section class="flex max-w-[480px] flex-wrap items-end gap-4 px-4 py-3">
          <UserForm v-model="userData" />
        </section>
        <div class="flex justify-center">
          <div
            class="flex flex-1 gap-3 flex-wrap px-4 py-3 max-w-[480px] justify-center"
          >
            <button
              class="flex min-w-[84px] max-w-[480px] cursor-pointer items-center justify-center overflow-hidden rounded-full h-12 px-5 bg-[#019863] text-[#FFFFFF] text-base font-bold leading-normal tracking-[0.015em] grow"
              @click="saveUserData"
            >
              <span class="truncate">저장하기</span>
            </button>
            <button
              class="flex min-w-[84px] max-w-[480px] cursor-pointer items-center justify-center overflow-hidden rounded-full h-12 px-5 bg-[#F4EFE6] text-[#1C160C] text-base font-bold leading-normal tracking-[0.015em] grow"
              @click="goBack"
            >
              <span class="truncate">취소하기</span>
            </button>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import HeaderComponent from "@/components/common/Header.vue";
import UserForm from "@/components/user/UserForm.vue";
import { updateUser, getUserInfo } from "@/api"; // updateUser 함수 임포트

export default {
  name: "App",
  components: {
    HeaderComponent,
    UserForm,
  },
  data() {
    return {
      userData: {
        email: "",
        phone: "",
        gender: "male",
      },
    };
  },
  methods: {
    async saveUserData() {
      try {
        // 서버에 데이터 전송
        const response = await updateUser(this.userData);
        
        // 응답 처리 (예: 성공 메시지 표시)
        console.log("회원정보가 성공적으로 업데이트되었습니다:", response);
        alert("회원정보가 성공적으로 업데이트되었습니다.");
      } catch (error) {
        console.error("회원정보 업데이트에 실패했습니다:", error);
        if (error.response && error.response.data) {
          alert(`업데이트 실패: ${error.response.data}`);
        } else {
          alert("회원정보 업데이트에 실패했습니다. 다시 시도해주세요.");
        }
      }
    },
    goBack() {
      if (this.$router) {
        // Vue Router를 사용하는 경우
        this.$router.go(-1);
      } else {
        // Vue Router가 없는 경우
        window.history.back();
      }
    },
    async fetchUserInfo() {
      try {
        const data = await getUserInfo();
        // 서버 응답 형식에 따라 userData를 설정
        this.userData.email = data.email || "";
        this.userData.phone = data.phone || "";
        this.userData.gender = data.gender || "male";
      } catch (error) {
        console.error("사용자 정보를 가져오는 데 실패했습니다:", error);
        alert("사용자 정보를 가져오는 데 실패했습니다.");
      }
    },
  },
  mounted() {
    // 사용자 정보를 초기화 (예: 서버에서 현재 사용자 정보 가져오기)
    this.fetchUserInfo();
  },
};
</script>

<style>
/* 글로벌 스타일은 여기서 추가 */
</style>
