package com.campccino.dto.requestDto;

import lombok.Data;

@Data
public class ShopReviewRequest {
    private String userId;
    private int shopRate;
    private String comment;
}
