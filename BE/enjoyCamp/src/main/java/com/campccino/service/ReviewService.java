package com.campccino.service;

import java.util.List;

import com.campccino.dto.requestDto.CreateReviewRequestDto;
import com.campccino.dto.responseDto.ReviewDto;

public interface ReviewService {
	List<ReviewDto> getReviewsByCampId(int campId);

	void createReview(CreateReviewRequestDto createReviewRequestDto) throws Exception;
}
