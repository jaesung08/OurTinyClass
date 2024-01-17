package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.Board;
import com.otc.tinyclassroom.board.entity.BoardType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoradRepositoryTest2 {

    @Autowired
    private BoardRepository boardRepository;

    @DisplayName("게시물 저장 테스트")
    @Test
    void saveBoardTest() {

        // given
        Board board = Board.of("title test", "hello", 1L, BoardType.BOARD_FREE, 2);
        System.out.println("board = " + board);

        // when
        Board temp = boardRepository.save(board);

        // then
        System.out.println("temp = " + temp);
        Assertions.assertNotNull(temp.getId());
        Assertions.assertEquals(temp.getId(), 1L);
        Assertions.assertNotNull(temp.getCreatedAt());
        Assertions.assertNull(temp.getDeletedAt());
    }
}