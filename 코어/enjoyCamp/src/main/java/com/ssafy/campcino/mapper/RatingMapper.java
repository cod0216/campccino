package com.ssafy.campcino.mapper;

import com.ssafy.campcino.dto.responseDto.ProductRatingDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RatingMapper {
    ProductRatingDto findRatingByProductId(int productId);
    void updateRating(ProductRatingDto rating);
    void addRating(ProductRatingDto rating);
}
