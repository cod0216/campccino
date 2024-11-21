<!-- src/App.vue -->
<template>
  <router-view v-slot="{ Component, route }">
    <keep-alive include="CampSearchView">
      <component :is="Component" :key="route.fullPath" />
    </keep-alive>
  </router-view>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
/* 글로벌 스타일 */
</style>
