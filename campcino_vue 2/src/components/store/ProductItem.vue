<template>
  <div class="product-item border rounded-lg p-4 shadow hover:shadow-lg transition-shadow duration-300">
    <img
      :src="product.image"
      :alt="product.name"
      @error="handleImageError"
      class="w-full h-48 object-cover rounded"
    />
    <h3 class="mt-2 text-lg font-semibold">{{ product.name }}</h3>
    <p class="text-gray-700">${{ product.price.toFixed(2) }}</p>
    <p class="text-yellow-500">⭐ {{ product.rating }}</p>
  </div>
</template>

<script>
export default {
  name: "ProductItem",
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
  methods: {
    handleImageError(event) {
      event.target.src = "https://via.placeholder.com/400x300?text=Image+Not+Available"; // 대체 이미지 URL
    },
  },
};
</script>

<style scoped>
.product-item {
  /* 추가적인 스타일링이 필요하면 여기에 작성 */
}
</style>
