
package com.ssafy.campcino.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.campcino.dto.responseDto.CampDto;

import java.util.List;
@Mapper
public interface CampMapper {
    List<CampDto> findAll();
    List<CampDto> getSelect(int region, List<Integer> category, String text);
    
    CampDto getCampById(@Param("campId") int campId);
    
}
