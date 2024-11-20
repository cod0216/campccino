
package com.ssafy.campcino.mapper;

import com.ssafy.campcino.model.CategoryDto;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CategoryMapper {
    List<CategoryDto> findAll();
}
