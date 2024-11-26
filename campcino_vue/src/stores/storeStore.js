// src/stores/storeStore.js
import { defineStore } from "pinia";
import apiClient from "@/api"; // Axios 인스턴스 사용

export const useStoreStore = defineStore("storeStore", {
  state: () => ({
    stores: [],
    storeDetails: {}, // storeId를 키로 하는 상세 정보 캐시
    storeReviews: {}, // storeId를 키로 하는 리뷰 캐시
  }),
  actions: {
    // 모든 스토어 목록을 가져오는 함수
    async fetchStores() {
      try {
        const response = await apiClient.get("/stores");
        this.stores = response.data.map((store) => ({
          shopId: store.shopId,
          shopTitle: store.shopTitle,
          shopImg: store.shopImg,
          shopPrice: store.shopPrice,
          shopComment: store.shopComment,
          rating: store.rating || 0, // 백엔드의 평점 데이터 매핑
        }));
      } catch (error) {
        console.error("Failed to fetch stores:", error);
        throw error;
      }
    },

    // 특정 storeId의 상세 정보를 가져오는 함수 (캐싱 적용)
    async fetchStoreDetail(storeId) {
      // 캐시에 데이터가 없을 경우에만 API 호출
      if (!this.storeDetails[storeId]) {
        try {
          const response = await apiClient.get(`/stores/${storeId}`);
          this.storeDetails = {
            ...this.storeDetails,
            [storeId]: {
              shopId: response.data.shopId,
              shopTitle: response.data.shopTitle,
              shopImg: response.data.shopImg,
              shopPrice: response.data.shopPrice,
              shopComment: response.data.shopComment,
              rating: response.data.rating || 0, // shopRating 대신 rating 사용, 기본값 처리
              // 필요한 다른 필드들 추가
            },
          };
        } catch (error) {
          console.error(`Failed to fetch store detail for storeId ${storeId}:`, error);
          throw error;
        }
      }
      // 캐시에 데이터가 있으면 API 호출을 건너뜀
    },

    // 특정 storeId의 리뷰를 가져오는 함수 (캐싱 적용)
    async fetchStoreReviews(storeId) {
      // 캐시에 데이터가 없을 경우에만 API 호출
      if (!this.storeReviews[storeId]) {
        try {
          const response = await apiClient.get(`/stores/${storeId}/reviews`);
          this.storeReviews = {
            ...this.storeReviews,
            [storeId]: response.data.map((review) => ({
              reviewId: review.reviewId,
              shopRate: review.shopRate,
              comment: review.comment,
              userId: review.userId,
              createdAt: review.createdAt,
            })),
          };
          console.log(`Fetched and cached reviews for storeId ${storeId}:`, this.storeReviews[storeId]);
        } catch (error) {
          console.error(`Failed to fetch reviews for storeId ${storeId}:`, error);
          throw error;
        }
      }
      // 캐시에 데이터가 있으면 API 호출을 건너뜀
    },

    // 특정 storeId에 리뷰를 제출하는 함수
    async submitShopReview(storeId, review) {
      try {
        const response = await apiClient.post(`/stores/${storeId}/reviews`, review);
        // 제출된 리뷰를 캐시에 추가
        if (this.storeReviews[storeId]) {
          this.storeReviews[storeId].push({
            reviewId: response.data.reviewId,
            shopRate: response.data.shopRate,
            comment: response.data.comment,
            userId: response.data.userId,
            createdAt: response.data.createdAt,
          });
        } else {
          // 리뷰 캐시가 없으면 새 배열로 초기화
          this.storeReviews = {
            ...this.storeReviews,
            [storeId]: [
              {
                reviewId: response.data.reviewId,
                shopRate: response.data.shopRate,
                comment: response.data.comment,
                userId: response.data.userId,
                createdAt: response.data.createdAt,
              },
            ],
          };
        }
      } catch (error) {
        console.error(`Failed to submit review for storeId ${storeId}:`, error);
        throw error;
      }
    },

    // 특정 storeId의 리뷰를 삭제하는 함수
    async deleteShopReview(storeId, reviewId) {
      try {
        await apiClient.delete(`/stores/${storeId}/reviews/${reviewId}`);
        // 캐시에서 리뷰 제거
        if (this.storeReviews[storeId]) {
          this.storeReviews[storeId] = this.storeReviews[storeId].filter(review => review.reviewId !== reviewId);
        }
      } catch (error) {
        console.error(`Failed to delete review ${reviewId} for storeId ${storeId}:`, error);
        throw error;
      }
    },

    // 특정 storeId의 리뷰를 수정하는 함수
    async updateShopReview(storeId, updatedReview) {
      try {
        const response = await apiClient.put(`/stores/${storeId}/reviews/${updatedReview.reviewId}`, updatedReview);
        // 캐시에서 리뷰 업데이트
        if (this.storeReviews[storeId]) {
          const index = this.storeReviews[storeId].findIndex(review => review.reviewId === updatedReview.reviewId);
          if (index !== -1) {
            this.storeReviews[storeId][index] = {
              ...this.storeReviews[storeId][index],
              shopRate: response.data.shopRate,
              comment: response.data.comment,
              updatedAt: response.data.updatedAt,
            };
          }
        }
      } catch (error) {
        console.error(`Failed to update review ${updatedReview.reviewId} for storeId ${storeId}:`, error);
        throw error;
      }
    },

    // 캐시된 데이터를 강제로 새로고침하는 함수 (필요 시 사용)
    async refreshStoreDetail(storeId) {
      try {
        const response = await apiClient.get(`/stores/${storeId}`);
        this.storeDetails = {
          ...this.storeDetails,
          [storeId]: {
            shopId: response.data.shopId,
            shopTitle: response.data.shopTitle,
            shopImg: response.data.shopImg,
            shopPrice: response.data.shopPrice,
            shopComment: response.data.shopComment,
            rating: response.data.rating || 0,
            // 필요한 다른 필드들 추가
          },
        };
      } catch (error) {
        console.error(`Failed to refresh store detail for storeId ${storeId}:`, error);
        throw error;
      }
    },

    async refreshStoreReviews(storeId) {
      try {
        const response = await apiClient.get(`/stores/${storeId}/reviews`);
        this.storeReviews = {
          ...this.storeReviews,
          [storeId]: response.data.map((review) => ({
            reviewId: review.reviewId,
            shopRate: review.shopRate,
            comment: review.comment,
            userId: review.userId,
            createdAt: review.createdAt,
          })),
        };
      } catch (error) {
        console.error(`Failed to refresh reviews for storeId ${storeId}:`, error);
        throw error;
      }
    },
  },
  getters: {
    // 특정 storeId의 상세 정보를 반환하는 getter
    getStoreDetail: (state) => {
      return (storeId) => state.storeDetails[storeId] || null;
    },

    // 특정 storeId의 리뷰를 반환하는 getter
    getStoreReviews: (state) => {
      return (storeId) => state.storeReviews[storeId] || [];
    },
  },
});
