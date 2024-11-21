package com.ssafy.campcino.service;

import java.util.List;

import com.ssafy.campcino.dto.responseDto.ReviewDto;

public interface ReviewService {
	List<ReviewDto> getReviewsByCampId(int campId);
}
