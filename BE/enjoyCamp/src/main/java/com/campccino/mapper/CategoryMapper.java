
package com.campccino.mapper;

import com.campccino.model.CategoryEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CategoryMapper {
    List<CategoryEntity> findAll();
}
