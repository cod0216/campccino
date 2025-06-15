
package com.campccino.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.campccino.dto.requestDto.CreateReviewRequestDto;
import com.campccino.dto.responseDto.CampDto;
import com.campccino.dto.responseDto.CampReviewSummaryDto;
import com.campccino.dto.responseDto.PaginatedResponse;
import com.campccino.dto.responseDto.ReviewDto;
import com.campccino.mapper.CampMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampServiceImpl implements CampService {

	@Autowired
	private CampMapper campMapper;

	@Override
	public List<CampDto> getAllCamps() {
		return campMapper.findAll();
	}

	@Override
	public List<CampDto> getSelectCamps(int region, List<Integer> category, String text) {
		return campMapper.getSelect(region, category, text);
	}

	@Override
	public CampDto getCampById(int campId) {
		return campMapper.getCampById(campId);
	}

	@Override
	public PaginatedResponse<CampReviewSummaryDto> getCampReviewSummaries(String sortBy, String sortOrder, int page,
                                                                          int size) {
		int offset = (page - 1) * size;
		Map<String, Object> params = new HashMap<>();
		params.put("sortBy", sortBy);
		params.put("sortOrder", sortOrder);
		params.put("limit", size);
		params.put("offset", offset);
		List<CampReviewSummaryDto> items = campMapper.getCampReviewSummaries(params);
		long totalItems = campMapper.getCampReviewSummariesCount(params);
		int totalPages = (int) Math.ceil((double) totalItems / size);
		return new PaginatedResponse<>(items, page, size, totalItems, totalPages);
	}

	// 새로 추가된 메서드
	@Override
	public PaginatedResponse<ReviewDto> getPaginatedReviewsByCampId(int campId, int page, int size) {
		int offset = (page - 1) * size;
		Map<String, Object> params = new HashMap<>();
		params.put("campId", campId);
		params.put("limit", size);
		params.put("offset", offset);
		List<ReviewDto> items = campMapper.getReviewsByCampIdPaginated(params);
		int totalItems = campMapper.getReviewCountByCampId(campId);
		int totalPages = (int) Math.ceil((double) totalItems / size);
		return new PaginatedResponse<>(items, page, size, totalItems, totalPages);
	}
	
	@Override
    public void createReview(CreateReviewRequestDto createReviewRequestDto) {
        campMapper.insertReview(createReviewRequestDto);
    }
}
