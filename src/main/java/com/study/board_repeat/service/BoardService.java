package com.study.board_repeat.service;

import com.study.board_repeat.entity.Board;
import com.study.board_repeat.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
//Service는  Repository에서 얻어온 정보를 바탕으로
//자바 문법을 이용하여 가공 후 다시 Controller에게 정보를 보내는 곳입니다.
public class BoardService {
    @Autowired //new를 써야하지만, 스프링부트가 알아서 읽어와서 주입을해준다.
    private BoardRepository boardRepository;
    public void write(Board board, MultipartFile file) throws Exception{
        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
        String projectPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";

        /*식별자 . 랜덤으로 이름 만들어줌*/
        UUID uuid=UUID.randomUUID();

        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
        String fileName=uuid+"_"+file.getOriginalFilename();

        /*빈 껍데기 생성*/
        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
        File saveFile=new File(projectPath,fileName);

        file.transferTo(saveFile);
        /*디비에 파일 넣기*/
        board.setFilename(fileName);

        /*저장되는 경로*/
        /*저장된파일의이름,저장된파일의경로*/
        board.setFilepath("/files/"+fileName);
        
        /*파일 저장*/
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

