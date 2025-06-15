package com.campccino.model;

import java.time.LocalDateTime;

import com.campccino.dto.responseDto.CampDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewEntity {

	    private Integer reviewId;

	    private Integer campRate;

	    private String comment;

	    private LocalDateTime createdAt;

	    private LocalDateTime updatedAt;

	    private UserEntity userEntity;

	    private CampDto campDto;
}
