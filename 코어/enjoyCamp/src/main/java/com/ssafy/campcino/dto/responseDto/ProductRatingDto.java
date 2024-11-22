package com.ssafy.campcino.dto.responseDto;

import lombok.Data;

@Data
public class ProductRatingDto {
    private int ratingId;
    private int productId;
    private double averageRating;
    private int totalReviews;
}
