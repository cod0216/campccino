<template>
  <div class="overflow-x-auto">
    <table class="min-w-full bg-white">
      <thead>
        <tr class="w-full bg-gray-100 border-b">
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">시설명</th>
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">지번 주소</th>
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">찜하기</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(camp, index) in camps" :key="camp.id" class="border-b">
          <td @click="$emit('focus-marker', index)" class="p-4 text-sm text-[#1C160C] cursor-pointer hover:underline">{{ camp.name }}</td>
          <td class="p-4 text-sm text-[#1C160C]">{{ camp.address }}</td>
          <td class="p-4">
            <button @click="$emit('toggle-favorite', camp.id)" class="flex items-center gap-2 px-4 py-2 border rounded">
              <span v-if="camp.isFavorite" class="text-red-600">&#9829; 찜하기 취소</span>
              <span v-else class="text-gray-600">&#9825; 찜하기</span>
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "CampList",
  props: {
    camps: Array,
  },
  emits: ["toggle-favorite", "focus-marker"],
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
