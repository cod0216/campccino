// src/main/java/com/ssafy/campcino/mapper/BoardMapper.java
package com.campccino.mapper;

import java.util.List;

import com.campccino.model.BoardEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
    List<BoardEntity> getBoards(
        @Param("categories") List<String> categories,
        @Param("text") String text,
        @Param("size") int size,
        @Param("offset") int offset,
        @Param("sortBy") String sortBy,
        @Param("sortOrder") String sortOrder
    );
    
    BoardEntity getBoardById(@Param("boardId") Long boardId);
    
    void insertBoard(BoardEntity board);
    
    void updateBoardView(@Param("view") int view, @Param("boardId") Long boardId);
    
    int countBoards(
        @Param("categories") List<String> categories,
        @Param("text") String text
    );
    
    void updateBoard(BoardEntity board);

    void deleteBoard(@Param("boardId") Long boardId);

}
