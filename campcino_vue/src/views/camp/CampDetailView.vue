<template>
  <div class="camp-detail-container">
    <Header />
    <main class="container mx-auto p-6">
      <div class="camp-info">
        <h1 class="text-4xl font-bold">{{ camp.title }}</h1>
        <RatingStars :rating="camp.rating" />
        <p class="text-base text-gray-700">{{ camp.description }}</p>
      </div>
      <AmenitiesList :amenities="camp.amenities" />
    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import RatingStars from "@/components/RatingStars.vue";
import AmenitiesList from "@/components/AmenitiesList.vue";

export default {
  name: "CampDetailView",
  components: {
    Header,
    RatingStars,
    AmenitiesList,
  },
  data() {
    return {
      camp: {
        title: "서울 근교 캠핑장",
        rating: 4.5,
        description: "서울에서 가깝고 자연을 느낄 수 있는 캠핑장입니다.",
        amenities: ["화장실", "샤워장", "바베큐 시설", "전기"],
      },
    };
  },
};
</script>

<style scoped>
/* 스타일 추가 필요 시 */
</style>
