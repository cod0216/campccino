<template>
  <div class="border p-4 rounded cursor-pointer hover:shadow-lg">
    <img :src="product.imgUrl" alt="Related Item" class="w-full h-40 object-cover mb-4 rounded" />
    <h3 class="text-lg font-bold">{{ product.name }}</h3>
    <p class="text-green-600">{{ product.price }}원</p>
  </div>
</template>

<script>
export default {
  name: "RelatedProductItem",
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
