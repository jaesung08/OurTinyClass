package com.otc.tinyclassroom.board.repository;

import com.otc.tinyclassroom.board.entity.Board;
import com.otc.tinyclassroom.board.entity.BoardType;
import com.otc.tinyclassroom.board.repository.BoardRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BoardRepositoryTest {

    @Mock
    private BoardRepository boardRepository;

    @BeforeEach
    void setUp() {
        // MockitoAnnotations.openMocks(this); // 이 부분을 삭제해도 됩니다. 이미 @ExtendWith(MockitoExtension.class)를 사용하고 있기 때문에 불필요합니다.
    }


    @DisplayName("게시물 저장 테스트")
    @Test
    void saveBoardTest() {
        // given
        Board board = Board.of("title test", "hello", 1L, BoardType.BOARD_FREE, 5);
        // ID나 created_at 같은 값은 명시적으로 설정
        board.setId(1L);
        board.setCreatedAt(LocalDate.from(LocalDateTime.now()));

        Mockito.when(boardRepository.save(Mockito.any())).thenReturn(board);

        // when
        Board temp = boardRepository.save(board);

        // theN
        // 검증
        System.out.println("temp = " + temp);
        Mockito.verify(boardRepository, Mockito.times(1)).save(Mockito.argThat(savedBoard -> {
            // 인자로 전달된 savedBoard에 대한 추가적인 검증 수행
            Assertions.assertEquals(1L, savedBoard.getId(), "ID가 1로 저장되지 않았습니다.");
            Assertions.assertNotNull(savedBoard.getCreatedAt(), "CREATED_AT이 null입니다.");
            Assertions.assertNull(savedBoard.getDeletedAt(), "DeletedAt이 Null이 아닙니다.");


            return true; // 검증을 통과하면 true를 리턴
        }));

    }
}
