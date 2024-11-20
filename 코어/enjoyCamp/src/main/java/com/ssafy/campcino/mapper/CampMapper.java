
package com.ssafy.campcino.mapper;

import com.ssafy.campcino.model.CampDto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface CampMapper {
    List<CampDto> findAll();
    List<CampDto> getSelect(int region, List<Integer> category, String text);
    
    CampDto getCampById(@Param("campId") int campId);
    
}
