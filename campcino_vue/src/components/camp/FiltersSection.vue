<template>
  <div class="flex items-center gap-4 mb-6">
    <select v-model="region" class="p-2 border rounded">
      <option v-for="region in regions" :key="region" :value="region">
        {{ region }}
      </option>
    </select>
    <select v-model="category" class="p-2 border rounded">
      <option v-for="category in categories" :key="category" :value="category">
        {{ category }}
      </option>
    </select>
    <input type="text" v-model="query" placeholder="검색어" class="p-2 border rounded flex-1" />
    <button @click="onSearch" class="bg-green-600 text-white px-6 py-2 rounded">검색</button>
  </div>
</template>

<script>
export default {
  name: "FiltersSection",
  props: {
    regions: {
      type: Array,
      default: () => ["전체"]
    },
    categories: {
      type: Array,
      default: () => ["전체"]
    },
  },
  emits: ["search"],
  data() {
    return {
      region: "전체",
      category: "전체",
      query: "",
    };
  },
  methods: {
    onSearch() {
      this.$emit("search", this.region, this.category, this.query);
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
