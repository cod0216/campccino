
<template>
  <div class="flex w-screen h-screen">
    <div class="flex-[2] flex items-center justify-center bg-campccino-0 glow">
      <img
            src="@/assets/logo4_no.png"
            alt="Campcino"
            class="logo"
            @click="goToLogin"
      />
    </div>
    <div class="flex-[1] flex items-center justify-center bg-campccino-bg glow">
      <LoginView/>
    </div>

  </div>
</template>

<script>
import LoginView from '@/views/user/LoginView.vue';

export default {
  name: "MainView",
  components : {LoginView,
  },
  methods: {

  },
};
</script>