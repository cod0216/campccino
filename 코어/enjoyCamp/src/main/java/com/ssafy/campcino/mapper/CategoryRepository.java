
package com.ssafy.campcino.repository;

import com.ssafy.campcino.model.CategoryDto;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CategoryRepository {
    List<CategoryDto> findAll();
}
