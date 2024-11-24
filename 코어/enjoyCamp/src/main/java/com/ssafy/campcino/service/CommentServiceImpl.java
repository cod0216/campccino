// src/main/java/com/ssafy/campcino/service/impl/CommentServiceImpl.java
package com.ssafy.campcino.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.campcino.dto.requestDto.CreateCommentRequestDto;
import com.ssafy.campcino.dto.responseDto.CommentDto;
import com.ssafy.campcino.dto.responseDto.PaginatedResponse;
import com.ssafy.campcino.mapper.CommentMapper;
import com.ssafy.campcino.model.CommentEntity;
import com.ssafy.campcino.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public CommentDto createComment(CreateCommentRequestDto createCommentRequestDto) {
        CommentEntity comment = new CommentEntity();
        comment.setBoardId(createCommentRequestDto.getBoardId());
        // userId는 인증된 사용자에서 설정 (예시로 "user123" 설정, 실제로는 Authentication에서 가져와야 함)
        comment.setUserId("user123"); // 추후 수정 필요
        comment.setCommentContent(createCommentRequestDto.getCommentContent());
        comment.setCommentCreatedAt(LocalDateTime.now());

        commentMapper.insertComment(comment);

        return convertToDto(comment);
    }

    @Override
    public PaginatedResponse<CommentDto> getCommentsByBoardId(Long boardId, int page, int size) {
        int offset = (page - 1) * size;
        List<CommentEntity> comments = commentMapper.getCommentsByBoardId(boardId, offset, size);
        int totalComments = commentMapper.countCommentsByBoardId(boardId);
        int totalPages = (int) Math.ceil((double) totalComments / size);

        List<CommentDto> commentDtos = comments.stream().map(this::convertToDto).collect(Collectors.toList());

        PaginatedResponse<CommentDto> response = new PaginatedResponse<>();
        response.setItems(commentDtos);
        response.setCurrentPage(page);
        response.setTotalPages(totalPages);
        response.setTotalItems(totalComments);

        return response;
    }

    private CommentDto convertToDto(CommentEntity comment) {
        CommentDto dto = new CommentDto();
        dto.setCommentId(comment.getCommentId());
        dto.setBoardId(comment.getBoardId());
        dto.setUserId(comment.getUserId());
        dto.setCommentContent(comment.getCommentContent());
        dto.setCommentCreatedAt(comment.getCommentCreatedAt());
        return dto;
    }
}
