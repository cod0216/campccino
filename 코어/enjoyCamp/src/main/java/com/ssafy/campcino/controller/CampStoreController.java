package com.ssafy.campcino.controller;

import com.ssafy.campcino.dto.responseDto.CampProductDto;
import com.ssafy.campcino.service.CampStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store")
public class CampStoreController {

    @Autowired
    private CampStoreService campStoreService;

    /**
     * 캠핑 용품 목록 조회
     * @return List<CampProductDto>
     */
    @GetMapping("/")
    public List<CampProductDto> getCampProducts() {
        System.out.println("Fetching all camp products...");
        List<CampProductDto> temp = campStoreService.getAllCampProducts();
        System.out.println("Returning Products: " + temp);
        return temp;
    }

    /**
     * 캠핑 용품 상세 조회
     * @param productId 캠핑 용품 ID
     * @return CampProductDto
     */
    @GetMapping("/{id}")
    public CampProductDto getCampProductById(@PathVariable("id") int productId) {
        System.out.println("Fetching camp product by ID: " + productId);
        return campStoreService.getCampProductById(productId);
    }

    /**
     * 랜덤 캠핑 용품 추천
     * @return List<CampProductDto>
     */
    @GetMapping("/recommend")
    public List<CampProductDto> getRandomProducts() {
        System.out.println("Fetching random camp products...");
        return campStoreService.getRandomProducts();
    }

    /**
     * 캠핑 용품 리뷰 추가
     * @param reviewDto 리뷰 데이터
     * @return String
     */
    @PostMapping("/reviews")
    public String addReview(@RequestBody ReviewDto reviewDto) {
        System.out.println("Adding review: " + reviewDto);
        // 리뷰 추가 로직 (예: 서비스 호출)
        // 예시: campStoreService.addReview(reviewDto);
        return "Review added successfully";
    }
}
