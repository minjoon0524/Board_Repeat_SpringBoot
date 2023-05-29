package com.study.board_repeat.controller;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception {
        boardService.write(board,file);

        //메세지 띄우기
        model.addAttribute("message","글작성이 완료되었습니다. ");
        model.addAttribute("searchUrl","/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
            /*page : default페이지, size : 한 페이지 게시글수, sort: 정렬기준컬럼, direction : 정렬순서*/
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                                Pageable pageable, String searchKeyword){
        Page<Board> list = null;

        /*searchKeyword = 검색하는 단어*/
        if(searchKeyword == null){
            list = boardService.boardList(pageable); //기존의 리스트보여줌
        }else{
            list = boardService.boardSearchList(searchKeyword, pageable); //검색리스트반환
        }
        /*페이징3*/
        int nowPage=list.getPageable().getPageNumber()+1;
        int startPage=Math.max(nowPage-4,1);
        int endPage=Math.min(nowPage+5,list.getTotalPages());

        //BoardService에서 만들어준 boardList가 반환되는데, list라는 이름으로 받아서 넘기겠다는 뜻
        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model,MultipartFile file) throws Exception {
        //기존에 있던 글이 담겨져서 온다.
        Board boardTemp=boardService.boardView(id);

        //기존에 있던 내용을 새로운 내용으로 덮어씌운다.
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getTitle());

        //추가 → 수정한내용을 boardService의 write부분에 넣기
        boardService.write(boardTemp,file);
        model.addAttribute("message","글수정이 완료되었습니다.");
        model.addAttribute("searchUrl","/board/list");

        return "message";

    }
}
