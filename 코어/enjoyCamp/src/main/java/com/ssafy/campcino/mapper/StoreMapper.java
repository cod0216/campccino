// com.ssafy.campcino.mapper.StoreMapper.java
package com.ssafy.campcino.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.campcino.dto.requestDto.ShopReviewRequest;
import com.ssafy.campcino.dto.responseDto.ShopReviewResponse;
import com.ssafy.campcino.dto.responseDto.StoreDto;

@Mapper
public interface StoreMapper {
    List<StoreDto> findAllStores();
    StoreDto findStoreById(int id);

    // 리뷰 관련 메서드 추가
    List<Integer> findRatingsByShopId(int shopId);
    void insertStoreReview(@Param("shopId") int shopId, @Param("userId") String userId, @Param("shopRate") int shopRate, @Param("comment") String comment);
    List<ShopReviewResponse> findStoreReviewsByShopId(int shopId);

    // Overloaded method to accept ShopReviewRequest
    default void insertStoreReview(int shopId, ShopReviewRequest reviewRequest) {
        insertStoreReview(shopId, reviewRequest.getUserId(), reviewRequest.getShopRate(), reviewRequest.getComment());
    }
}
