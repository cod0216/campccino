// src/stores/storeStore.js
import { defineStore } from 'pinia';
import axios from 'axios';

export const useStoreStore = defineStore('storeStore', {
  state: () => ({
    stores: [],
    storeDetail: null,
    storeReviews: [],
  }),
  actions: {
    async fetchStores() {
      try {
        const response = await axios.get('/api/stores');
        this.stores = response.data.map(store => ({
            shopId: store.shopId,
            shopTitle: store.shopTitle,
            shopImg: store.shopImg,
            shopPrice: store.shopPrice,
            shopComment: store.shopComment,
            rating: store.rating || 0, // 백엔드의 평점 데이터 매핑
          }));
      } catch (error) {
        console.error('Failed to fetch stores:', error);
        throw error;
      }
    },
    async fetchStoreDetail(id) {
      try {
        const response = await axios.get(`/api/stores/${id}`);
        this.storeDetail = {
            shopId: response.data.shopId,
            shopTitle: response.data.shopTitle,
            shopImg: response.data.shopImg,
            shopPrice: response.data.shopPrice,
            shopComment: response.data.shopComment,
            rating: response.data.rating || 0, // shopRating 대신 rating 사용, 기본값 처리
            // 필요한 다른 필드들 추가
          };
      } catch (error) {
        console.error('Failed to fetch store detail:', error);
        throw error;
      }
    },
    async fetchStoreReviews(id) {
        try {
          const response = await axios.get(`/api/stores/${id}/reviews`);
          console.log("API 응답 데이터 (리뷰):", response.data); // 디버깅용 로그 추가
          this.storeReviews = response.data.map(review => ({
            reviewId: review.reviewId,
            shopRate: review.shopRate,
            comment: review.comment,
            userId: review.userId,
            createdAt: review.createdAt,
          }));
          console.log("매핑된 리뷰 데이터:", this.storeReviews); // 매핑 결과 확인
        } catch (error) {
          console.error("리뷰 데이터를 가져오는 중 오류 발생:", error);
        }
      },
    async submitShopReview(shopId, review) {
      try {
        await axios.post(`/api/stores/${shopId}/reviews`, review);
        // 리뷰 제출 후 리뷰 리스트를 다시 불러옵니다.
        await this.fetchStoreReviews(shopId);
      } catch (error) {
        console.error('Failed to submit shop review:', error);
        throw error;
      }
    },
  },
});
