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
          shopId: store.shopId, // 또는 store.shop_id -> shopId
          shopTitle: store.shopTitle, // 또는 store.shop_title
          shopImg: store.shopImg, // 또는 store.shop_img
          shopPrice: store.shopPrice, // 또는 store.shop_price
          shopComment: store.shopComment, // 또는 store.shop_comment
          shopRating: store.shopRating, // 또는 store.shop_rating
          // 필요한 다른 필드들...
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
          shopRating: response.data.shopRating,
          // 필요한 다른 필드들...
        };
      } catch (error) {
        console.error('Failed to fetch store detail:', error);
        throw error;
      }
    },
    async fetchStoreReviews(id) {
      try {
        const response = await axios.get(`/api/stores/${id}/reviews`);
        this.storeReviews = response.data.map(review => ({
          reviewId: review.reviewId, // 또는 review.review_id
          shopRate: review.shopRate, // 또는 review.shop_rate
          comment: review.comment,
          userId: review.userId, // 또는 review.user_id
          createdAt: review.createdAt, // 또는 review.created_at
          // 필요한 다른 필드들...
        }));
      } catch (error) {
        console.error('Failed to fetch store reviews:', error);
        throw error;
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
