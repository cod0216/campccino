<template>
    <div
      class="filter-item px-4 py-2 border rounded-lg cursor-pointer hover:bg-gray-100"
      :class="{ 'bg-gray-200 font-bold': isSelected }"
      @click="toggleSelection"
    >
      {{ filter }}
    </div>
  </template>
  
  <script>
  export default {
    name: "FilterItem",
    props: {
      filter: {
        type: String,
        required: true,
      },
    },
    data() {
      return {
        isSelected: false, // 필터 선택 여부
      };
    },
    methods: {
      toggleSelection() {
        this.isSelected = !this.isSelected;
        // 부모 컴포넌트로 선택 상태를 알리기 위해 이벤트 전달
        this.$emit("filter-toggled", { filter: this.filter, selected: this.isSelected });
      },
    },
  };
  </script>
  
  <style scoped>
  .filter-item {
    transition: background-color 0.2s, color 0.2s;
  }
  .filter-item:hover {
    background-color: #f1f1f1;
  }
  </style>
  