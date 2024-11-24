<template>
  <div
    class="min-h-screen flex flex-col bg-white"
    style='font-family: "Be Vietnam Pro", "Noto Sans", sans-serif;'
  >
    <Header />

    <main class="px-10 py-5 flex-1">
      <h2 class="text-gray-900 text-2xl font-bold mb-4">
        Gear up for camping season
      </h2>
      <div class="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-4 gap-4">
        <ProductItem
          v-for="product in products"
          :key="product.id"
          :product="product"
        />
      </div>
    </main>
  </div>
</template>

<script>
import Header from "@/components/common/Header.vue";
import ProductItem from "@/components/store/ProductItem.vue";

export default {
  name: "CampStore",
  components: {
    Header,
    ProductItem,
  },
  data() {
    return {
      products: [ // 'const' 제거하고 'products'로 직접 정의
        {
          id: 1,
          name: "REI Co-op Half Dome 2 Plus 텐트",
          price: 299.95,
          image: "https://images.unsplash.com/photo-1509223197845-458d87318791?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 2,
          name: "REI Co-op Magma 15 슬리핑 백",
          price: 349.95,
          image: "https://images.unsplash.com/photo-1491553895911-0055eca6402d?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 3,
          name: "Jetboil Flash 스토브 쿠킹 시스템",
          price: 109.95,
          image: "https://images.unsplash.com/photo-1519681393784-d120267933ba?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 4,
          name: "Osprey Atmos AG 65 팩",
          price: 270.0,
          image: "https://images.unsplash.com/photo-1542281286-9e0a16bb7366?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 5,
          name: "캠핑 장비 5번",
          price: 127.5,
          image: "https://images.unsplash.com/photo-1514516874013-3c4f2f5b9c7b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 3.9,
        },
        {
          id: 6,
          name: "Big Agnes Copper Spur HV UL3 텐트",
          price: 399.99,
          image: "https://images.unsplash.com/photo-1515886657613-9f0dc3e1e8af?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 7,
          name: "Therm-a-Rest NeoAir XTherm 매트",
          price: 229.95,
          image: "https://images.unsplash.com/photo-1519052538982-4ec1a5a78a4f?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 8,
          name: "MSR Hubba Hubba NX 2 텐트",
          price: 349.95,
          image: "https://images.unsplash.com/photo-1508921912186-1d1a45ebb3c1?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 9,
          name: "Snow Peak 멀티가스 버너",
          price: 89.95,
          image: "https://images.unsplash.com/photo-1542362567-b07e54358753?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.4,
        },
        {
          id: 10,
          name: "Sea to Summit Lightweight 쿨러",
          price: 159.99,
          image: "https://images.unsplash.com/photo-1523731407965-2430cd12f5e4?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.2,
        },
        {
          id: 11,
          name: "Goal Zero Yeti 500X 파워 스테이션",
          price: 499.99,
          image: "https://images.unsplash.com/photo-1590522536734-9efde45581e1?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 12,
          name: "Black Diamond Spot 350 랜턴",
          price: 39.95,
          image: "https://images.unsplash.com/photo-1601758064308-7d5d3a9b2191?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 13,
          name: "Eureka Copper Canyon 6P 텐트",
          price: 279.99,
          image: "https://images.unsplash.com/photo-1521295121783-8a321d551ad2?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 14,
          name: "NEMO Dagger 2P 텐트",
          price: 249.95,
          image: "https://images.unsplash.com/photo-1506744038136-46273834b3fb?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 15,
          name: "Katadyn BeFree 필터",
          price: 49.95,
          image: "https://images.unsplash.com/photo-1523906630133-6cfeb375af45?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 16,
          name: "Vango Banshee 200 텐트",
          price: 99.99,
          image: "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.1,
        },
        {
          id: 17,
          name: "Coleman Sundome 텐트",
          price: 119.95,
          image: "https://images.unsplash.com/photo-1518185320098-91c95f7a8c8f?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.0,
        },
        {
          id: 18,
          name: "Hydro Flask 보틀",
          price: 39.95,
          image: "https://images.unsplash.com/photo-1579487189309-8ce17b7c4388?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.9,
        },
        {
          id: 19,
          name: "Primus Lite+ 쿨러",
          price: 129.99,
          image: "https://images.unsplash.com/photo-1509042239860-f550ce710b93?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.2,
        },
        {
          id: 20,
          name: "Marmot Trestles 20 슬리핑 백",
          price: 199.95,
          image: "https://images.unsplash.com/photo-1502082553048-f009c37129b9?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 21,
          name: "Kelty Salida 2 팩",
          price: 199.99,
          image: "https://images.unsplash.com/photo-1517991104121-bbbfc9285a2b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.4,
        },
        {
          id: 22,
          name: "Snow Peak Titanium 컵",
          price: 29.95,
          image: "https://images.unsplash.com/photo-1518991792437-fc1127d66b59?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 23,
          name: "MSR PocketRocket 2 스토브",
          price: 44.95,
          image: "https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 24,
          name: "BioLite CampStove 2",
          price: 169.95,
          image: "https://images.unsplash.com/photo-1523868758551-84b2e22e5b17?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 25,
          name: "Leki Micro Vario 트레킹 폴",
          price: 89.95,
          image: "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 26,
          name: "Therm-a-Rest Z Lite Sol 매트",
          price: 59.95,
          image: "https://images.unsplash.com/photo-1522335789203-aabd1fc54bc9?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 27,
          name: "Gregory Baltoro 65 팩",
          price: 330.0,
          image: "https://images.unsplash.com/photo-1503387762-592deb58ef4e?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 28,
          name: "Stanley Adventure 캠핑 텀블러",
          price: 25.99,
          image: "https://images.unsplash.com/photo-1598970434795-0c54fe7c0648?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.4,
        },
        {
          id: 29,
          name: "Black Diamond Alpine Carbon 백패커 폴",
          price: 129.95,
          image: "https://images.unsplash.com/photo-1516222338250-4d5e57b2d57f?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 30,
          name: "MSR Lightning Ascent 스토브",
          price: 199.95,
          image: "https://images.unsplash.com/photo-1525977933960-4c0e4ff2a65d?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 31,
          name: "Nalgene Wide Mouth 병",
          price: 12.95,
          image: "https://images.unsplash.com/photo-1516245834210-c4c142787335?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.9,
        },
        {
          id: 32,
          name: "REI Co-op Camp 4 방수 매트",
          price: 89.95,
          image: "https://images.unsplash.com/photo-1505483531331-4e2bb1a19ae6?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 33,
          name: "Jetboil MiniMo 스토브",
          price: 109.95,
          image: "https://images.unsplash.com/photo-1516979187457-637abb4f9353?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 34,
          name: "YETI Hopper Flip 쿨러",
          price: 249.99,
          image: "https://images.unsplash.com/photo-1524169358529-d5f9d1814e6d?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 35,
          name: "CamelBak Crux 3L 물통",
          price: 29.95,
          image: "https://images.unsplash.com/photo-1493244040629-496f6d136cc3?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 36,
          name: "Coleman Gas 버너",
          price: 59.99,
          image: "https://images.unsplash.com/photo-1528920004556-548e16219b2b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.2,
        },
        {
          id: 37,
          name: "Sea to Summit X-Pot 쿨링 매트",
          price: 39.95,
          image: "https://images.unsplash.com/photo-1517365830460-955ce3ccd263?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.4,
        },
        {
          id: 38,
          name: "Vargo Titanium 테이블",
          price: 69.95,
          image: "https://images.unsplash.com/photo-1508614999368-926005129b56?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 39,
          name: "Klymit Insulated 매트",
          price: 79.95,
          image: "https://images.unsplash.com/photo-1487730116645-74489c95b41b?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 40,
          name: "Goal Zero Lighthouse 랜턴",
          price: 89.99,
          image: "https://images.unsplash.com/photo-1487951427454-4d5f454a2c8e?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 41,
          name: "Lodge Cast Iron 스킬렛",
          price: 34.95,
          image: "https://images.unsplash.com/photo-1518991792437-fc1127d66b59?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 42,
          name: "Soto WindMaster 스토브",
          price: 159.95,
          image: "https://images.unsplash.com/photo-1519336555928-1c8fa7f1a1c8?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 43,
          name: "Therm-a-Rest MondoMax 매트",
          price: 119.95,
          image: "https://images.unsplash.com/photo-1517685352821-92cf88aee5a5?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.4,
        },
        {
          id: 44,
          name: "Black Diamond Trail Pro HF 헤드램프",
          price: 49.95,
          image: "https://images.unsplash.com/photo-1580927752454-3b2f9b95d1b0?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.7,
        },
        {
          id: 45,
          name: "GSI Outdoors Pinnacle 프라이팬 세트",
          price: 89.95,
          image: "https://images.unsplash.com/photo-1523986371872-9d3ba2e2e9f2?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
        {
          id: 46,
          name: "ENO DoubleNest 해먹",
          price: 99.95,
          image: "https://images.unsplash.com/photo-1504215680853-026ed2a45def?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.8,
        },
        {
          id: 47,
          name: "Sea to Summit Ultra-Sil 압축백",
          price: 29.95,
          image: "https://images.unsplash.com/photo-1517275209661-d15a2eae80d7?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.5,
        },
        {
          id: 48,
          name: "Marmot Limestone 4P 텐트",
          price: 329.95,
          image: "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.3,
        },
        {
          id: 49,
          name: "Kovea X-200 스토브",
          price: 119.95,
          image: "https://images.unsplash.com/photo-1526336024174-e58f5cdd8e13?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.2,
        },
        {
          id: 50,
          name: "REI Co-op Flash 55 팩",
          price: 220.0,
          image: "https://images.unsplash.com/photo-1512336714731-489689fd1ca8?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=60",
          rating: 4.6,
        },
      ],
    };
  },
};
</script>

<style scoped>
/* Custom styles can be added here if needed */
.product-item img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-item h3 {
  margin-top: 0.5rem;
  font-size: 1.1rem;
  font-weight: bold;
}

.product-item p {
  margin: 0.25rem 0;
}
</style>
