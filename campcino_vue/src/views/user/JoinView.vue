<template>
  <div
    class="relative flex w-full min-h-screen flex-col bg-white overflow-x-hidden"
    style="font-family: 'Noto Serif', 'Noto Sans', sans-serif"
  >
    <div class="layout-container flex h-full grow flex-col">
      <div class="px-40 flex flex-1 justify-center py-5">
        <div
          class="layout-content-container flex flex-col w-full max-w-[512px] py-5 flex-1"
        >
          <h1
            class="text-[#1C160C] text-2xl font-bold leading-tight tracking-[-0.015em] px-4 text-center pb-3 pt-5"
          >
            회원 가입
          </h1>

          <!-- Form Fields -->
          <div
            class="flex flex-col items-start gap-4 px-4 py-3"
            v-for="(field, index) in fields"
            :key="index"
          >
            <label class="flex flex-col w-full">
              <p
                class="text-[#1C160C] text-base font-medium leading-normal pb-2"
              >
                {{ field.label }}
              </p>
              <component
                :is="field.type === 'select' ? 'select' : 'input'"
                :type="field.inputType"
                :placeholder="field.placeholder"
                class="w-full p-2 border border-gray-300 rounded"
                v-model="formData[field.model]"
              >
                <option
                  v-for="option in field.options"
                  :key="option.value"
                  :value="option.value"
                >
                  {{ option.label }}
                </option>
              </component>
            </label>
          </div>

          <!-- 회원가입 버튼 -->
          <div class="flex px-4 py-3">
            <button
              @click="handleSubmit"
              class="w-full py-2 mt-4 bg-green-600 text-white font-bold rounded"
            >
              회원 가입
            </button>
          </div>

          <p
            class="text-[#A18249] text-sm font-normal leading-normal pb-3 pt-1 px-4 text-center underline cursor-pointer"
            @click="goToLogin"
          >
            이미 계정이 있으신가요? 로그인하기
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";

export default {
  name: "UserJoin",
  components: {
    Header,
  },
  data() {
    return {
      formData: {
        username: "",
        password: "",
        confirmPassword: "",
        ssn: "",
        phone: "",
        age: null,
        email: "",
        gender: "male",
      },
      fields: [
        {
          label: "아이디 입력",
          type: "input",
          inputType: "text",
          placeholder: "아이디를 입력하세요",
          model: "username",
        },
        {
          label: "비밀번호",
          type: "input",
          inputType: "password",
          placeholder: "비밀번호",
          model: "password",
        },
        {
          label: "비밀번호 확인",
          type: "input",
          inputType: "password",
          placeholder: "비밀번호 확인",
          model: "confirmPassword",
        },
        {
          label: "주민번호",
          type: "input",
          inputType: "password",
          placeholder: "123454-1234456",
          model: "ssn",
        },
        {
          label: "휴대전화",
          type: "input",
          inputType: "text",
          placeholder: "010-0000-0000",
          model: "phone",
        },
        {
          label: "나이",
          type: "input",
          inputType: "number",
          placeholder: "나이",
          model: "age",
        },
        {
          label: "이메일",
          type: "input",
          inputType: "email",
          placeholder: "choissafy@ssafy.com",
          model: "email",
        },
        {
          label: "성별",
          type: "select",
          model: "gender",
          options: [
            { value: "male", label: "남성" },
            { value: "female", label: "여성" },
          ],
        },
      ],
    };
  },
  methods: {
    handleSubmit() {
      // Form submission logic
      console.log("Form Data:", this.formData);
    },
    goToLogin() {
      // Navigate to the login page
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
