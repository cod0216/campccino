<template>
  <div class="overflow-x-auto">
    <table class="min-w-full bg-white">
      <thead>
        <tr class="w-full bg-gray-100 border-b">
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">시설명</th>
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">
            지번 주소
          </th>
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">찜하기</th>
          <th class="p-4 text-left text-sm font-bold text-[#1C160C]">
            자세히 보기
          </th>
          <!-- 새 열 추가 -->
        </tr>
      </thead>
      <tbody>
        <tr v-for="camp in camps" :key="camp.id" class="border-b">
          <td
            @click="$emit('focus-marker', camp.id)"
            class="p-4 text-sm text-[#1C160C] cursor-pointer hover:underline"
          >
            {{ camp.name }}
          </td>
          <td class="p-4 text-sm text-[#1C160C]">{{ camp.address }}</td>
          <td class="p-4">
            <button class="flex items-center gap-2 px-4 py-2 border rounded">
              <span class="text-gray-600">&#9825; 찜하기</span>
            </button>
          </td>
          <td class="p-4">
            <button
              @click="viewDetails(camp.id)"
              class="text-blue-500 hover:underline"
            >
              자세히 보기
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
    camps: {
      type: Array,
      required: true,
    },
  },
  emits: ["focus-marker"],
  methods: {
    viewDetails(campId) {
      this.$router.push({ name: "CampDetail", params: { id: campId } });
    },
  },
};
</script>

<style scoped>
/* 필요한 스타일 추가 */
</style>
