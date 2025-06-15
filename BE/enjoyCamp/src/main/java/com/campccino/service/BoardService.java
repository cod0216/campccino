// src/main/java/com/ssafy/campcino/service/BoardService.java
package com.campccino.service;

import java.util.List;

import com.campccino.dto.requestDto.CreateBoardRequestDto;
import com.campccino.dto.responseDto.BoardDto;
import com.campccino.dto.responseDto.PaginatedResponse;

public interface BoardService {
    // 게시글 목록 조회
    PaginatedResponse<BoardDto> getBoards(List<String> categories, String text, int page, int size, String sortBy, String sortOrder);
    
    // 특정 게시글 조회
    BoardDto getBoardById(Long boardId);
    
    // 게시글 작성
    BoardDto createBoard(CreateBoardRequestDto createBoardRequestDto);
    
    // 게시글 조회수 업데이트
    void updateBoardView(Long boardId);
    
    void updateBoard(CreateBoardRequestDto requestDto, Long boardId);

    void deleteBoard(Long boardId);

}
