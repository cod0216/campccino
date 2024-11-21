<!-- src/components/camp/FavoriteList.vue -->
<template>
  <div class="bg-white shadow-md rounded p-4">
    <h2 class="text-lg font-bold mb-4">찜 목록</h2>
    <ul>
      <li v-for="camp in favorites" :key="camp.id" class="mb-2">
        <div class="flex justify-between items-center">
          <span>{{ camp.name }}</span>
          <button
            @click="$emit('remove-favorite', camp.id)"
            class="text-red-500 hover:text-red-700"
          >
            삭제
          </button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "FavoriteList",
  props: {
    favorites: {
      type: Array,
      required: true,
    },
  },
  emits: ["remove-favorite"],
};
</script>

<style scoped>
/* 필요한 스타일을 추가하세요 */
</style>
