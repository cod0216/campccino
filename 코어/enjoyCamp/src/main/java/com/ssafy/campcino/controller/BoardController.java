// src/main/java/com/ssafy/campcino/controller/BoardController.java
package com.ssafy.campcino.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.campcino.dto.requestDto.CreateBoardRequestDto;
import com.ssafy.campcino.dto.requestDto.CreateCommentRequestDto;
import com.ssafy.campcino.dto.responseDto.BoardDto;
import com.ssafy.campcino.dto.responseDto.CommentDto;
import com.ssafy.campcino.dto.responseDto.PaginatedResponse;
import com.ssafy.campcino.service.BoardService;
import com.ssafy.campcino.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    /**
     * 게시글 목록 조회 (페이징, 필터링)
     * 
     * @param categories  카테고리 필터
     * @param text        검색 텍스트
     * @param page        페이지 번호 (기본값: 1)
     * @param size        페이지 당 게시글 수 (기본값: 10)
     * @param sortBy      정렬 기준 (예: "board_created_at")
     * @param sortOrder   정렬 순서 ("ASC" 또는 "DESC")
     * @return PaginatedResponse<BoardDto>
     */
    @GetMapping
    public PaginatedResponse<BoardDto> getBoards(
            @RequestParam(required = false) List<String> categories,
            @RequestParam(defaultValue = "") String text,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "board_created_at") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder) {
        // 기본 카테고리 설정 (필요 시 수정)
        if (categories == null || categories.isEmpty()) {
            categories = Arrays.asList("일반", "질문", "공지", "자유", "정보", "기타");
        }
        return boardService.getBoards(categories, text, page, size, sortBy, sortOrder);
    }

    /**
     * 특정 게시글 조회
     * 
     * @param boardId 게시글 ID
     * @return BoardDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable("id") Long boardId) {
        try {
            BoardDto board = boardService.getBoardById(boardId);
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 게시글 작성
     * 
     * @param request 게시글 작성 요청 데이터
     * @return BoardDto
     */
    @PostMapping()
    public ResponseEntity<BoardDto> createBoard(@RequestBody CreateBoardRequestDto request, Authentication authentication) {
        try {
            // 인증된 사용자 ID 설정
//            String userId = authentication.getName();
            System.out.println("체꾸");
            String userId = "cod0216";
            request.setUserId(userId);
            BoardDto createdBoard = boardService.createBoard(request);
            return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 댓글 목록 조회 (페이징 적용)
     * 
     * @param boardId 게시글 ID
     * @param page    페이지 번호 (기본값: 1)
     * @param size    페이지 당 댓글 수 (기본값: 10)
     * @return PaginatedResponse<CommentDto>
     */
    @GetMapping("/{id}/comments")
    public PaginatedResponse<CommentDto> getCommentsByBoardId(
            @PathVariable("id") Long boardId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return commentService.getCommentsByBoardId(boardId, page, size);
    }

    /**
     * 댓글 작성
     * 
     * @param boardId        게시글 ID
     * @param request        댓글 작성 요청 데이터
     * @param authentication 인증된 사용자 정보
     * @return ResponseEntity<String>
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<String> createComment(
            @PathVariable("id") Long boardId,
            @Valid @RequestBody CreateCommentRequestDto request,
            Authentication authentication) {
        try {
            // 인증된 사용자 ID 설정
//            String userId = authentication.getName();
            String userId = "ssafy";
        	
            request.setBoardId(boardId);
            request.setUserId(userId);
            commentService.createComment(request);
            return new ResponseEntity<>("댓글이 성공적으로 작성되었습니다!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("댓글 작성에 실패했습니다: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
