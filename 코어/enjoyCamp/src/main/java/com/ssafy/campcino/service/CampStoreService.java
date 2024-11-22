package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.responseDto.CampProductDto;

import java.util.List;


public interface CampStoreService {
    List<CampProductDto> getAllCampProducts();
    CampProductDto getCampProductById(int productId);
    List<CampProductDto> getRandomProducts();
}
