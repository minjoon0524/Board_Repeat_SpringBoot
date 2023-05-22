package com.study.board_repeat.service;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//Service는  Repository에서 얻어온 정보를 바탕으로
//자바 문법을 이용하여 가공 후 다시 Controller에게 정보를 보내는 곳입니다.
public class BoardService {
    @Autowired //new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;
    public void write(Board board){
        boardRepository.save(board);

    }
}

