
package com.ssafy.campcino.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.campcino.dto.responseDto.CampDto;
import com.ssafy.campcino.dto.responseDto.CampReviewSummaryDto;
import com.ssafy.campcino.dto.responseDto.PaginatedResponse;
import com.ssafy.campcino.dto.responseDto.ReviewDto;
import com.ssafy.campcino.model.AddressEntity;
import com.ssafy.campcino.model.CategoryEntity;
import com.ssafy.campcino.model.SidoEntity;
import com.ssafy.campcino.service.AddressService;
import com.ssafy.campcino.service.CampService;
import com.ssafy.campcino.service.CategoryService;
import com.ssafy.campcino.service.SidoService;

@RestController
@RequestMapping("/api")
public class CampController {

	@Autowired
	private CampService campService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SidoService sidoService;

	// Camp endpoints
//    @GetMapping("/camps")
//    public List<CampDto> getAllCamps() {
//        return campService.getAllCamps();
//    }

	@GetMapping("/camps")
	public List<CampDto> getSelectCamps(@RequestParam(required = false, defaultValue = "0") int region,
			@RequestParam(required = false) List<Integer> category,
			@RequestParam(required = false, defaultValue = "") String text) {
		if (category == null || category.isEmpty()) {
			category = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
		}
		return campService.getSelectCamps(region, category, text);
	}

	@GetMapping("/camps/{id}")
	public CampDto getCampById(@PathVariable("id") int campId) {
		return campService.getCampById(campId);
	}

	// Address endpoints
	@GetMapping("/camps/{id}/address")
	public AddressEntity getAddressByCampId(@PathVariable int id) {
		return addressService.getAddressByCampId(id);
	}

	// Category endpoints
	@GetMapping("/categories")
	public List<CategoryEntity> getAllCategories() {
		return categoryService.getAllCategories();
	}

	// Region endpoints
	@GetMapping("/regions")
	public List<SidoEntity> getAllRegions() {
		return sidoService.getAllRegions();
	}

	// 새로운 리뷰 요약 엔드포인트
	@GetMapping("/reviews")
	public PaginatedResponse<CampReviewSummaryDto> getCampReviewSummaries(
			@RequestParam(defaultValue = "campName") String sortBy,
			@RequestParam(defaultValue = "asc") String sortOrder, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "15") int size) {
		return campService.getCampReviewSummaries(sortBy, sortOrder, page, size);
	}

	// 새로운 paginated reviews 엔드포인트
	@GetMapping("/camps/{id}/reviews")
	public PaginatedResponse<ReviewDto> getPaginatedReviewsByCampId(@PathVariable("id") int campId,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
		return campService.getPaginatedReviewsByCampId(campId, page, size);
	}

}
