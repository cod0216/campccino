<template>
  <Header />
  <div class="camp-store-detail-container">
    <main class="container mx-auto p-6">
      <div v-if="item" class="store-item-info">
        <!-- 아이템 이름 -->
        <h1 class="text-4xl font-bold">{{ item.name }}</h1>

        <!-- 이미지 갤러리 (주 이미지 포함) -->
        <div class="flex w-full grow bg-[#FFFFFF] p-4 gap-6 flex-wrap">
          <div class="w-full md:w-2/3 rounded overflow-hidden bg-cover">
            <img
              :src="item.imgUrl"
              alt="Item Image"
              class="w-full h-auto rounded"
            />
          </div>
          <div class="w-full md:w-1/3 flex flex-col gap-4">
            <div
              v-for="(url, index) in item.additionalImages"
              :key="index"
              class="rounded overflow-hidden bg-cover"
            >
              <img
                :src="url"
                alt="Additional Image"
                class="w-full h-40 object-cover rounded"
              />
            </div>
          </div>
        </div>

        <!-- 아이템 설명과 가격 -->
        <div class="flex flex-wrap p-4 gap-6 items-start">
          <p class="text-lg text-gray-700 flex-1">{{ item.description }}</p>
          <p class="text-3xl font-semibold text-green-600">
            {{ item.price }}원
          </p>
        </div>

        <!-- 아이템 상세 정보 -->
        <div
          class="p-4 grid grid-cols-2 gap-x-8 gap-y-4 border-t border-solid border-t-[#E9DFCE]"
        >
          <div class="flex flex-col">
            <span class="text-[#A18249] font-medium">최소 무게</span>
            <span class="text-[#1C160C]">{{ item.weight }}</span>
          </div>
          <div class="flex flex-col">
            <span class="text-[#A18249] font-medium">재질</span>
            <span class="text-[#1C160C]">{{ item.material }}</span>
          </div>
          <div class="flex flex-col">
            <span class="text-[#A18249] font-medium">크기</span>
            <span class="text-[#1C160C]">{{ item.size }}</span>
          </div>
          <div class="flex flex-col">
            <span class="text-[#A18249] font-medium">적용 인원</span>
            <span class="text-[#1C160C]">{{ item.capacity }}</span>
          </div>
        </div>

        <!-- 리뷰 섹션 -->
        <div class="flex flex-col p-4 gap-6 mt-6">
          <h2 class="text-[22px] font-bold">사용자 리뷰</h2>
          <ReviewItem
            v-for="(review, index) in item.reviews"
            :key="index"
            :review="review"
          />
        </div>

        <!-- 관련 제품 섹션 -->
        <div class="p-4">
          <h2 class="text-[22px] font-bold mb-4">관련 제품</h2>
          <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
            <RelatedProductItem
              v-for="relatedItem in item.relatedItems"
              :key="relatedItem.id"
              :product="relatedItem"
              @click="navigateToProduct(relatedItem.id)"
            />
          </div>
        </div>
      </div>
      <div v-else>
        <p>문제가 생겼습니다. 아이템을 찾을 수 없습니다.</p>
      </div>
    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";

export default {
  name: "CampStoreDetailView",
  components: {
    Header,
  },
  props: ["id"], // 라우터에서 전달받은 id
  data() {
    return {
      items: [
        {
          id: 1,
          name: "캠핑용 모기장",
          price: 50000,
          imgUrl:
            "https://cdn.pixabay.com/photo/2019/10/03/11/14/camp-4522970_1280.jpg",
          description:
            "캠핑 시 모기와 벌레로부터 효과적으로 보호해주는 모기장입니다.",
          weight: "1.2kg",
          material: "폴리에스터",
          size: "200 x 150cm",
          capacity: "2인용",
          additionalImages: [
            "https://example.com/mosquito-net-1.jpg",
            "https://example.com/mosquito-net-2.jpg",
          ],
          reviews: [
            {
              username: "Jane Doe",
              date: "2024-10-11",
              rating: 5,
              comment:
                "매우 편리하고 설치가 쉬웠습니다. 야외에서도 정말 유용했어요!",
            },
            {
              username: "John Smith",
              date: "2024-10-01",
              rating: 4,
              comment: "가격 대비 성능이 좋습니다. 모기 걱정이 없어졌어요.",
            },
          ],
          relatedItems: [
            {
              id: 2,
              name: "다용도 수납 박스",
              price: 30000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCzh_bqSVwUixWlNdulxIwPSnlIHeQhLoJvw&s",
            },
            {
              id: 3,
              name: "캠핑용 의자",
              price: 45000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqUSQx7C-UcFc0pfZv-pB5IFn07RtKhvrDAw&s",
            },
          ],
        },
        {
          id: 2,
          name: "다용도 수납 박스",
          price: 30000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRCzh_bqSVwUixWlNdulxIwPSnlIHeQhLoJvw&s",
          description:
            "캠핑 장비와 소품을 깔끔하게 정리할 수 있는 다용도 수납 박스입니다.",
          weight: "1.5kg",
          material: "플라스틱",
          size: "60 x 40 x 35cm",
          capacity: "50L",
          additionalImages: [
            "https://example.com/storage-box-1.jpg",
            "https://example.com/storage-box-2.jpg",
          ],
          reviews: [
            {
              username: "Alice",
              date: "2024-09-15",
              rating: 5,
              comment:
                "수납력이 좋아 캠핑 장비를 정리하는 데 큰 도움이 되었어요!",
            },
            {
              username: "Bob",
              date: "2024-09-10",
              rating: 4,
              comment:
                "튼튼하고 크기가 적당합니다. 다만 접는 부분이 조금 뻑뻑해요.",
            },
          ],
          relatedItems: [
            {
              id: 1,
              name: "캠핑용 모기장",
              price: 50000,
              imgUrl:
                "https://cdn.pixabay.com/photo/2019/10/03/11/14/camp-4522970_1280.jpg",
            },
            {
              id: 4,
              name: "휴대용 가스 버너",
              price: 35000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh06XCJC2nsNYztwg8Ymx2iszx7EM_HqWpFw&s",
            },
          ],
        },
        {
          id: 3,
          name: "캠핑용 의자",
          price: 45000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqUSQx7C-UcFc0pfZv-pB5IFn07RtKhvrDAw&s",
          description: "편안한 휴식을 위한 캠핑용 접이식 의자입니다.",
          weight: "2kg",
          material: "알루미늄 프레임과 폴리에스터 시트",
          size: "50 x 60 x 90cm",
          capacity: "100kg",
          additionalImages: [
            "https://example.com/camping-chair-1.jpg",
            "https://example.com/camping-chair-2.jpg",
          ],
          reviews: [
            {
              username: "Charlie",
              date: "2024-08-20",
              rating: 5,
              comment: "앉기 편하고 가벼워서 이동이 편리합니다.",
            },
            {
              username: "Daisy",
              date: "2024-08-18",
              rating: 4,
              comment: "프레임이 튼튼하고 안정감이 있어요. 가격도 적당합니다.",
            },
          ],
          relatedItems: [
            {
              id: 5,
              name: "캠핑용 테이블",
              price: 55000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxDPyMIx8mm8wvwgOeTTKnrjuyhjiWzeXiVw&s",
            },
            {
              id: 6,
              name: "아웃도어 랜턴",
              price: 25000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGAG1M2pHblCAO73P99ED6xmPgd1BxxQ6hwA&s",
            },
          ],
        },
        {
          id: 4,
          name: "휴대용 가스 버너",
          price: 35000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh06XCJC2nsNYztwg8Ymx2iszx7EM_HqWpFw&s",
          description: "캠핑 시 야외 요리를 위한 휴대용 가스 버너입니다.",
          weight: "1kg",
          material: "스테인리스 스틸",
          size: "25 x 20 x 10cm",
          capacity: "1구",
          additionalImages: [
            "https://example.com/gas-burner-1.jpg",
            "https://example.com/gas-burner-2.jpg",
          ],
          reviews: [
            {
              username: "Eve",
              date: "2024-08-05",
              rating: 5,
              comment: "화력이 강하고 조작이 쉬워서 야외 요리에 최적입니다.",
            },
            {
              username: "Frank",
              date: "2024-07-30",
              rating: 4,
              comment: "작고 가벼워서 캠핑에 딱 좋습니다.",
            },
          ],
          relatedItems: [
            {
              id: 3,
              name: "캠핑용 의자",
              price: 45000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqUSQx7C-UcFc0pfZv-pB5IFn07RtKhvrDAw&s",
            },
            {
              id: 7,
              name: "침낭 세트",
              price: 80000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK9ovjbpBs4-fd-cdGjlt-MDEg3fJmS88SHg&s",
            },
          ],
        },
        {
          id: 5,
          name: "캠핑용 테이블",
          price: 55000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxDPyMIx8mm8wvwgOeTTKnrjuyhjiWzeXiVw&s",
          description: "야외 식사와 활동을 위한 접이식 캠핑 테이블입니다.",
          weight: "3kg",
          material: "알루미늄",
          size: "120 x 60 x 70cm",
          capacity: "4인용",
          additionalImages: [
            "https://example.com/camping-table-1.jpg",
            "https://example.com/camping-table-2.jpg",
          ],
          reviews: [
            {
              username: "George",
              date: "2024-07-15",
              rating: 5,
              comment:
                "튼튼하고 조립이 쉬워서 아주 좋습니다. 캠핑에서 유용하게 썼어요!",
            },
            {
              username: "Hannah",
              date: "2024-07-10",
              rating: 4,
              comment: "높이 조절이 가능해서 유용합니다.",
            },
          ],
          relatedItems: [
            {
              id: 8,
              name: "캠핑용 식기 세트",
              price: 20000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtuuLDVJPOmp0SG38K5llfKvVCHJf_hVuFWg&s",
            },
            {
              id: 10,
              name: "캠핑용 해먹",
              price: 30000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRQB7smyQ8HH72QO9wrM0WHE7IsrPx21BEFg&s",
            },
          ],
        },
        {
          id: 6,
          name: "아웃도어 랜턴",
          price: 25000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGAG1M2pHblCAO73P99ED6xmPgd1BxxQ6hwA&s",
          description:
            "어두운 야외 환경에서 밝은 조명을 제공하는 아웃도어 랜턴입니다.",
          weight: "0.8kg",
          material: "플라스틱",
          size: "15 x 15 x 25cm",
          capacity: "LED, 200루멘",
          additionalImages: [
            "https://example.com/outdoor-lantern-1.jpg",
            "https://example.com/outdoor-lantern-2.jpg",
          ],
          reviews: [
            {
              username: "Isla",
              date: "2024-06-25",
              rating: 5,
              comment: "밝기가 충분하고 방수 기능이 있어 캠핑에 최적입니다.",
            },
            {
              username: "Jack",
              date: "2024-06-20",
              rating: 4,
              comment: "배터리 수명이 길고 이동이 편리합니다.",
            },
          ],
          relatedItems: [
            {
              id: 9,
              name: "휴대용 냉장고",
              price: 150000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT90dUqLeYD3bgvvM2x2cSgWx5H7C_62rbhhQ&s",
            },
            {
              id: 11,
              name: "방수 매트",
              price: 40000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJZOvBK2rLHS8NZvOGiAV_Czp-UuFFOMIOZQ&s",
            },
          ],
        },
        {
          id: 7,
          name: "침낭 세트",
          price: 80000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQK9ovjbpBs4-fd-cdGjlt-MDEg3fJmS88SHg&s",
          description: "캠핑 시 따뜻하고 편안한 수면을 위한 침낭 세트입니다.",
          weight: "2.5kg",
          material: "폴리에스터",
          size: "220 x 80cm",
          capacity: "1인용",
          additionalImages: [
            "https://example.com/sleeping-bag-1.jpg",
            "https://example.com/sleeping-bag-2.jpg",
          ],
          reviews: [
            {
              username: "Katy",
              date: "2024-06-10",
              rating: 5,
              comment: "매우 따뜻하고 가볍습니다. 여행 시 아주 좋습니다.",
            },
            {
              username: "Leo",
              date: "2024-06-05",
              rating: 4,
              comment: "편안하고 보온이 잘 됩니다. 다만 지퍼가 조금 불편해요.",
            },
          ],
          relatedItems: [
            {
              id: 4,
              name: "휴대용 가스 버너",
              price: 35000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSh06XCJC2nsNYztwg8Ymx2iszx7EM_HqWpFw&s",
            },
            {
              id: 12,
              name: "캠핑용 주전자",
              price: 15000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTy8tgFry5SxBpiYLcjZkfs3B-4nuUqM_6lNw&s",
            },
          ],
        },
        {
          id: 8,
          name: "캠핑용 식기 세트",
          price: 20000,
          imgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtuuLDVJPOmp0SG38K5llfKvVCHJf_hVuFWg&s",
          description: "캠핑 시 필요한 식기를 모두 포함한 식기 세트입니다.",
          weight: "0.5kg",
          material: "스테인리스",
          size: "다양한 크기",
          capacity: "4인용",
          additionalImages: [
            "https://example.com/camping-dishes-1.jpg",
            "https://example.com/camping-dishes-2.jpg",
          ],
          reviews: [
            {
              username: "Mia",
              date: "2024-05-30",
              rating: 5,
              comment: "내구성 좋고 실용적입니다. 캠핑용으로 딱입니다!",
            },
            {
              username: "Noah",
              date: "2024-05-28",
              rating: 4,
              comment:
                "가볍고 사용하기 편리합니다. 다만 포크 크기가 조금 작아요.",
            },
          ],
          relatedItems: [
            {
              id: 5,
              name: "캠핑용 테이블",
              price: 55000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxDPyMIx8mm8wvwgOeTTKnrjuyhjiWzeXiVw&s",
            },
            {
              id: 10,
              name: "캠핑용 해먹",
              price: 30000,
              imgUrl:
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRQB7smyQ8HH72QO9wrM0WHE7IsrPx21BEFg&s",
            },
          ],
        },
      ],
      item: null, // 선택된 아이템 정보 저장
    };
  },
  created() {
    // 컴포넌트가 생성될 때 데이터 로드
    this.fetchItem(this.id);
  },
  watch: {
    id(newId) {
      // id가 변경될 때마다 데이터를 다시 로드
      this.fetchItem(newId);
    },
  },
  methods: {
    fetchItem(id) {
      // 전달받은 id로 아이템 검색 및 저장
      this.item = this.items.find((i) => i.id === Number(id));
    },
    navigateToProduct(productId) {
      this.$router.push({ name: "CampStoreDetail", params: { id: productId } });
    },
  },
};
</script>
