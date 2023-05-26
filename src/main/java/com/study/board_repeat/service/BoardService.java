package com.study.board_repeat.service;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
//Service는  Repository에서 얻어온 정보를 바탕으로
//자바 문법을 이용하여 가공 후 다시 Controller에게 정보를 보내는 곳입니다.
public class BoardService {
    @Autowired //new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws Exception {
        String projectPath=System.getProperty("user.dir")+ "\\src\\main\\resources\\static\\files";

        UUID uuid=UUID.randomUUID();

        String fileName=uuid+"_"+file.getOriginalFilename();

        File saveFile=new File(projectPath,fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);

        board.setFilepath("\\src\\main\\resources\\static\\files\\"+fileName);

        boardRepository.save(board);

    }

    public List<Board> boardList(){
        //findAll : 테스트보드라는 클래스가 담긴 List를 반환하는것을 확인할수있다
        return boardRepository.findAll();
    }

    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}

