
package com.campccino.service;

import java.util.List;

import com.campccino.mapper.CategoryMapper;
import com.campccino.model.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryMapper.findAll();
    }
}
