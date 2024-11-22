package com.ssafy.campcino.mapper;

import com.ssafy.campcino.dto.responseDto.CampProductDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CampStoreMapper {
    List<CampProductDto> findAllCampProducts();
    CampProductDto findCampProductById(int productId);
    List<CampProductDto> findRandomProducts();
}
