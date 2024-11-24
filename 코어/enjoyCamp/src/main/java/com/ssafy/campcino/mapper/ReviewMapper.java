package com.ssafy.campcino.mapper;

import com.ssafy.campcino.dto.responseDto.ProductReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ProductReviewDto> findReviewsByProductId(@Param("productId") int productId);
    void addReview(ProductReviewDto reviewDto);
    void updateReview(ProductReviewDto reviewDto);
    void deleteReview(@Param("reviewId") int reviewId);
}
