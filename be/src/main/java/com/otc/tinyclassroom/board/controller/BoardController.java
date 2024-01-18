package com.otc.tinyclassroom.board.controller;

import com.otc.tinyclassroom.board.dto.BoardSaveRequestDto;
import com.otc.tinyclassroom.board.dto.BoardSaveResponseDto;
import com.otc.tinyclassroom.board.service.BoardService;
import com.otc.tinyclassroom.global.model.response.BaseResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;


    @PostMapping("/write")
    public BaseResponse<BoardSaveResponseDto> save(@ModelAttribute BoardSaveRequestDto boardSaveRequestDto) {
        boardService.save(boardSaveRequestDto);
        return  BaseResponse.success(HttpStatus.CREATED.value(), "게시물 작성 성공", null);
    }


//    @DeleteMapping("/delete")
//    public String delete(@PathVariable Long id) {
//        boardService.delete(id);
//        return "";

}
