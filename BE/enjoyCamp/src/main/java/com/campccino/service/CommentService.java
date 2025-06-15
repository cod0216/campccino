// src/main/java/com/ssafy/campcino/service/CommentService.java
package com.campccino.service;

import com.campccino.dto.requestDto.CreateCommentRequestDto;
import com.campccino.dto.requestDto.UpdateCommentRequestDto;
import com.campccino.dto.responseDto.CommentDto;
import com.campccino.dto.responseDto.PaginatedResponse;

public interface CommentService {
    CommentDto createComment(CreateCommentRequestDto createCommentRequestDto);
    PaginatedResponse<CommentDto> getCommentsByBoardId(Long boardId, int page, int size);
    boolean updateComment(UpdateCommentRequestDto updateCommentRequestDto);
    boolean deleteComment(Long commentId, Long boardId);
}
