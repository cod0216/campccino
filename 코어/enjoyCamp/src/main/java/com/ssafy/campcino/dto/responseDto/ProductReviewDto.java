package com.ssafy.campcino.dto.responseDto;

import lombok.Data;

@Data
public class ProductReviewDto {
    private int reviewId;
    private int productId;
    private int userId;
    private int rating;
    private String comment;
    private String createdAt;
}
