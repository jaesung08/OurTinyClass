package com.otc.tinyclassroom.board.service;

import com.otc.tinyclassroom.board.dto.BoardSaveRequestDto;
import com.otc.tinyclassroom.board.entity.Board;
import com.otc.tinyclassroom.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void save(BoardSaveRequestDto boardSaveRequestDto) {
        // BoardSaveRequestDto에서 필요한 정보 추출
        String title = boardSaveRequestDto.title();
        String content = boardSaveRequestDto.content();

        // Board 엔티티 생성 및 데이터베이스에 저장
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        // writerId가 null이 아닐 경우에만 설정
        if (boardSaveRequestDto.writerId() != null) {
            board.setWriterId(boardSaveRequestDto.writerId());
        }

        boardRepository.save(board);

        System.out.println("Saving board with title: " + title + " and content: " + content);
    }

//    public void delete(Long id) {
//        // 데이터베이스에서 해당 ID에 해당하는 게시물을 삭제
//        boardRepository.deleteById(id);
//        System.out.println("Deleting board with ID: " + id);
//    }
}
