<template>
  <div
    class="relative flex size-full min-h-screen flex-col bg-[#FFFFFF] group/design-root overflow-x-hidden"
  >
    <Header />
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
          <UserForm />
        </section>
        <div class="flex justify-center">
          <div
            class="flex flex-1 gap-3 flex-wrap px-4 py-3 max-w-[480px] justify-center"
          >
            <button
              class="flex min-w-[84px] max-w-[480px] cursor-pointer items-center justify-center overflow-hidden rounded-full h-12 px-5 bg-[#019863] text-[#FFFFFF] text-base font-bold leading-normal tracking-[0.015em] grow"
            >
              <span class="truncate">저장하기</span>
            </button>
            <button
              class="flex min-w-[84px] max-w-[480px] cursor-pointer items-center justify-center overflow-hidden rounded-full h-12 px-5 bg-[#F4EFE6] text-[#1C160C] text-base font-bold leading-normal tracking-[0.015em] grow"
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

export default {
  name: "App",
  components: {
    HeaderComponent,
    UserForm,
  },
};
</script>

<style>
/* 글로벌 스타일은 여기서 추가 */
</style>
