// com.ssafy.campcino.service.StoreService.java
package com.campccino.service;

import java.util.List;

import com.campccino.dto.requestDto.ShopReviewRequest;
import com.campccino.dto.responseDto.ShopReviewResponse;
import com.campccino.dto.responseDto.StoreDto;

public interface StoreService {
    List<StoreDto> getAllStores();
    StoreDto getStoreById(int id);

    // 추가된 메서드
    void addStoreReview(int shopId, ShopReviewRequest reviewRequest);
    List<ShopReviewResponse> getStoreReviews(int shopId);


    // 추가된 리뷰 수정 및 삭제 메서드
    void updateStoreReview(int storeId, int reviewId, ShopReviewRequest reviewRequest);
    void deleteStoreReview(int storeId, int reviewId, String userId);
}
