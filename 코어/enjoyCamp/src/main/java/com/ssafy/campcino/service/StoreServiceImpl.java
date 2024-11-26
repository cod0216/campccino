// com.ssafy.campcino.service.StoreServiceImpl.java
package com.ssafy.campcino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.campcino.dto.requestDto.ShopReviewRequest;
import com.ssafy.campcino.dto.responseDto.ShopReviewResponse;
import com.ssafy.campcino.dto.responseDto.StoreDto;
import com.ssafy.campcino.mapper.StoreMapper;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public List<StoreDto> getAllStores() {
        List<StoreDto> stores = storeMapper.findAllStores();
        for (StoreDto store : stores) {
            store.setRating(calculateAverageRating(store.getShopId()));
            // store.setImage(store.getShopImg()); // 불필요한 코드 제거
        }
        return stores;
    }

    @Override
    public StoreDto getStoreById(int id) {
        StoreDto store = storeMapper.findStoreById(id);
        if (store != null) {
            store.setRating(calculateAverageRating(id));
            // store.setImage(store.getShopImg()); // 불필요한 코드 제거
        }
        return store;
    }

    @Override
    public void addStoreReview(int shopId, ShopReviewRequest reviewRequest) {
        storeMapper.insertStoreReview(shopId, reviewRequest);
    }

    @Override
    public List<ShopReviewResponse> getStoreReviews(int shopId) {
        return storeMapper.findStoreReviewsByShopId(shopId);
    }
    @Override
    public void updateStoreReview(int storeId, int reviewId, ShopReviewRequest reviewRequest) {
        // 리뷰 작성자 확인
        String currentUserId = reviewRequest.getUserId();
        String reviewAuthorId = storeMapper.findReviewAuthorByReviewId(reviewId);
        if (!currentUserId.equals(reviewAuthorId)) {
            throw new SecurityException("리뷰 수정 권한이 없습니다.");
        }
        storeMapper.updateStoreReview(reviewId, reviewRequest.getShopRate(), reviewRequest.getComment());
    }

    @Override
    public void deleteStoreReview(int storeId, int reviewId) {
        // 리뷰 작성자 확인 (삭제 요청 시 사용자 ID가 필요하므로, 이를 서비스 메서드의 인자로 받거나 다른 방법으로 전달해야 함)
        // 여기서는 예시로 사용자 ID를 추가로 받는다고 가정
        // 실제 구현 시 보안 고려가 필요함
        // 예: JWT 토큰에서 사용자 ID 추출
        throw new UnsupportedOperationException("삭제 메서드 구현 필요");
    }

    // 실제 삭제 메서드 구현 (사용자 ID를 추가로 받아야 함)
    public void deleteStoreReview(int storeId, int reviewId, String userId) {
        String reviewAuthorId = storeMapper.findReviewAuthorByReviewId(reviewId);
        if (!userId.equals(reviewAuthorId)) {
            throw new SecurityException("리뷰 삭제 권한이 없습니다.");
        }
        storeMapper.deleteStoreReview(reviewId);
    }

    // 상점의 평균 평점 계산
    private double calculateAverageRating(int shopId) {
        List<Integer> ratings = storeMapper.findRatingsByShopId(shopId);
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int rate : ratings) {
            sum += rate;
        }
        return sum / ratings.size();
    }
}
