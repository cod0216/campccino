// src/main/java/com/ssafy/campcino/service/impl/ReviewServiceImpl.java
package com.ssafy.campcino.service;

import com.ssafy.campcino.dto.responseDto.ReviewDto;
import com.ssafy.campcino.mapper.CampMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CampMapper campMapper;

    @Override
    public List<ReviewDto> getReviewsByCampId(int campId) {
        return campMapper.getReviewsByCampId(campId);
    }
}
