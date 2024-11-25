<template>
  <form @submit.prevent>
    <label class="flex flex-col min-w-40 flex-1">
      <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">이메일</p>
      <input
        v-model="localUserData.email"
        class="form-input"
        type="email"
        placeholder="이메일 입력"
      />
    </label>
    <label class="flex flex-col min-w-40 flex-1">
      <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">전화번호</p>
      <input
        v-model="localUserData.phone"
        class="form-input"
        type="tel"
        placeholder="전화번호 입력"
      />
    </label>
    <label class="flex flex-col min-w-40 flex-1">
      <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">성별</p>
      <select v-model="localUserData.gender" class="form-input">
        <option value="male">남성</option>
        <option value="female">여성</option>
      </select>
    </label>
    <!-- 폼 내 저장 버튼은 제거 (App.vue의 저장 버튼을 사용) -->
  </form>
</template>

<script>
export default {
  name: "UserForm",
  props: {
    modelValue: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      localUserData: { ...this.modelValue },
    };
  },
  watch: {
    localUserData: {
      handler(newData) {
        this.$emit("update:modelValue", newData);
      },
      deep: true,
    },
    modelValue: {
      handler(newVal) {
        this.localUserData = { ...newVal };
      },
      deep: true,
    },
  },
};
</script>

<style>
/* 컴포넌트 스타일은 여기서 추가 */
</style>
