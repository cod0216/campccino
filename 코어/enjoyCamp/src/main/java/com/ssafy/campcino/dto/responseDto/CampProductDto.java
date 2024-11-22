package com.ssafy.campcino.dto.responseDto;


import java.util.List;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CampProductDto {
    private int productId;               // 제품 ID
    private String productName;          // 제품 이름
    private int productCategory;         // 제품 카테고리
    private long productPrice;           // 제품 가격
    private String productImg;           // 제품 이미지 URL
    private String productDescription;   // 제품 설명

    // 추가된 필드
    private double averageRating;        // 평균 평점
    private int totalReviews;            // 총 리뷰 수
    private List<ProductReviewDto> reviews;     // 리뷰 목록

}
