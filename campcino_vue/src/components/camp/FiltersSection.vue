<!-- FiltersSection.vue -->
<template>
  <div class="flex flex-col md:flex-row items-center gap-4 mb-6">
    <select
      v-model="selectedRegionId"
      class="p-2 border rounded w-full md:w-auto"
    >
      <option :value="0">지역</option>
      <option
        v-for="region in regions"
        :key="region.sidoId"
        :value="region.sidoId"
      >
        {{ region.sidoName }}
      </option>
    </select>
    <select
      v-model="selectedCategoryId"
      class="p-2 border rounded w-full md:w-auto"
    >
      <option :value="0">카테고리</option>
      <option
        v-for="category in categories"
        :key="category.categoryId"
        :value="category.categoryId"
      >
        {{ category.categoryName }}
      </option>
    </select>
    <input
      type="text"
      v-model="query"
      placeholder="검색어 입력"
      class="p-2 border rounded flex-1 w-full md:w-auto"
    />
    <button
      @click="onSearch"
      class="bg-green-600 text-white px-4 py-2 rounded w-full md:w-auto"
    >
      검색
    </button>
  </div>
</template>

<script>
export default {
  name: "FiltersSection",
  props: {
    regions: {
      type: Array,
      default: () => [],
    },
    categories: {
      type: Array,
      default: () => [],
    },
  },
  emits: ["search"],
  data() {
    return {
      selectedRegionId: 0, // '전체 지역' 기본값
      selectedCategoryId: 0, // '전체 카테고리' 기본값
      query: "",
    };
  },
  methods: {
    onSearch() {
      this.$emit(
        "search",
        this.selectedRegionId,
        this.selectedCategoryId,
        this.query
      );
    },
  },
};
</script>

<style scoped>
/* 반응형 디자인을 위해 flex 레이아웃 사용 */
</style>
