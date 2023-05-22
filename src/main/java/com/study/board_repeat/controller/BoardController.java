package com.study.board_repeat.controller;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    @ResponseBody
    public String main(){
        return "Hello World";
    }
    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";

    }
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){
        boardService.write(board);
        return "";
    }

}
