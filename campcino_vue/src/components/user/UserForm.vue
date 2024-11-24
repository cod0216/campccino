<template>
    <form @submit.prevent="submitForm">
      <label class="flex flex-col min-w-40 flex-1">
        <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">이름</p>
        <input v-model="userData.name" class="form-input" type="text" placeholder="이름 입력" />
      </label>
      <label class="flex flex-col min-w-40 flex-1">
        <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">이메일</p>
        <input v-model="userData.email" class="form-input" type="email" placeholder="이메일 입력" />
      </label>
      <label class="flex flex-col min-w-40 flex-1">
        <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">전화번호</p>
        <input v-model="userData.phone" class="form-input" type="tel" placeholder="전화번호 입력" />
      </label>
      <label class="flex flex-col min-w-40 flex-1">
        <p class="text-[#1C160C] text-base font-medium leading-normal pb-2">성별</p>
        <select v-model="userData.gender" class="form-input">
          <option value="male">남성</option>
          <option value="female">여성</option>
        </select>
      </label>
      <button type="submit" class="btn-primary">저장</button>
    </form>
</template>

<script>
export default {
  data() {
    return {
      userData: {
        name: "",
        email: "",
        phone: "",
        gender: "male",
      },
    };
  },
  methods: {
    async submitForm() {
      try {
        console.log("User data submitted:", this.userData);
        alert("사용자 정보가 저장되었습니다.");
      } catch (error) {
        console.error("Error submitting user data:", error);
        alert("사용자 정보를 저장하는 데 실패했습니다.");
      }
    },
  },
};
</script>
