package com.study.board_repeat.controller;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/board/list")
    public String boardList(Model model){
        model.addAttribute("list",boardService.boardList());
        return "boardList";
    }
    @GetMapping("/board/view") // localhost:8090/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,Model model){
    model.addAttribute("board",boardService.boardView(id));
    return "boardmodify";

    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board){
        //기존에 있던 글이 담겨져서 온다.
        Board boardTemp=boardService.boardView(id);

        //기존에 있던 내용을 새로운 내용으로 덮어씌운다.
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getTitle());

        //추가 → 수정한내용을 boardService의 write부분에 넣기
        boardService.write(boardTemp);
        return "redirect:/board/list" ;

    }
}
