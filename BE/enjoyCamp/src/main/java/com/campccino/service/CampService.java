
package com.campccino.service;

import java.util.List;

import com.campccino.dto.requestDto.CreateReviewRequestDto;
import com.campccino.dto.responseDto.CampDto;
import com.campccino.dto.responseDto.CampReviewSummaryDto;
import com.campccino.dto.responseDto.PaginatedResponse;
import com.campccino.dto.responseDto.ReviewDto;

public interface CampService {
    List<CampDto> getAllCamps();
    List<CampDto> getSelectCamps(int region, List<Integer> category, String text);
    CampDto getCampById(int campId);
    PaginatedResponse<CampReviewSummaryDto> getCampReviewSummaries(String sortBy, String sortOrder, int page, int size);
 // 새로 추가된 메서드
    PaginatedResponse<ReviewDto> getPaginatedReviewsByCampId(int campId, int page, int size);
    // 리뷰 작성 메서드 추가
    void createReview(CreateReviewRequestDto createReviewRequestDto);
}
