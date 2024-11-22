package com.ssafy.campcino.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewRequestDto {
    private Integer campId;
    private Integer campRate;
    private String comment;
    private String userId; // 사용자 ID
}
