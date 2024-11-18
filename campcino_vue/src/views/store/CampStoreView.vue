<template>
  <Header />
  <div class="store-view-container">
    <main class="container mx-auto p-6">
      <h1 class="text-4xl font-bold">캠핑 용품 목록</h1>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mt-6">
        <div v-for="item in items" :key="item.id" class="border p-4 rounded cursor-pointer hover:shadow-lg"
          @click="goToDetail(item)">
          <img :src="item.imgUrl" alt="Item Image" class="w-full h-40 object-cover mb-4" />
          <h2 class="text-xl font-bold">{{ item.name }}</h2>
          <p class="text-lg">가격: {{ item.price }} 원</p>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";

export default {
  name: "CampStoreView",
  components: {
    Header,
  },
  data() {
    return {
      items: [
        {
          id: 1,
          name: "캠핑용 모기장",
          price: 50000,
          imgUrl: "https://cdn.pixabay.com/photo/2019/10/03/11/14/camp-4522970_1280.jpg",
        },
        {
          id: 2,
          name: "다용도 수납 박스",
          price: 30000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCzh_bqSVwUixWlNdulxIwPSnlIHeQhLoJvw&s",
        },
        {
          id: 3,
          name: "캠핑용 의자",
          price: 45000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqUSQx7C-UcFc0pfZv-pB5IFn07RtKhvrDAw&s",
        },
        {
          id: 4,
          name: "휴대용 가스 버너",
          price: 35000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh06XCJC2nsNYztwg8Ymx2iszx7EM_HqWpFw&s",
        },
        {
          id: 5,
          name: "캠핑용 테이블",
          price: 55000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxDPyMIx8mm8wvwgOeTTKnrjuyhjiWzeXiVw&s",
        },
        {
          id: 6,
          name: "아웃도어 랜턴",
          price: 25000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGAG1M2pHblCAO73P99ED6xmPgd1BxxQ6hwA&s",
        },
        {
          id: 7,
          name: "침낭 세트",
          price: 80000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK9ovjbpBs4-fd-cdGjlt-MDEg3fJmS88SHg&s",
        },
        {
          id: 8,
          name: "캠핑용 식기 세트",
          price: 20000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtuuLDVJPOmp0SG38K5llfKvVCHJf_hVuFWg&s",
        },
        {
          id: 9,
          name: "휴대용 냉장고",
          price: 150000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT90dUqLeYD3bgvvM2x2cSgWx5H7C_62rbhhQ&s",
        },
        {
          id: 10,
          name: "캠핑용 해먹",
          price: 30000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRQB7smyQ8HH72QO9wrM0WHE7IsrPx21BEFg&s",
        },
        {
          id: 11,
          name: "방수 매트",
          price: 40000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJZOvBK2rLHS8NZvOGiAV_Czp-UuFFOMIOZQ&s",
        },
        {
          id: 12,
          name: "캠핑용 주전자",
          price: 15000,
          imgUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy8tgFry5SxBpiYLcjZkfs3B-4nuUqM_6lNw&s",
        },
      ],
    };
  },
  methods: {
    goToDetail(item) {
      this.$router.push({
        name: "CampStoreDetail",
        params: { id: item.id },
        props: { item },
      });
    },
  },
};
</script>

<style scoped>

</style>
