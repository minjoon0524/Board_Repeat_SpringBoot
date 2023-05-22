package com.study.board_repeat.repository;

import com.study.board_repeat.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//어떤엔티티를 넣을것이냐, 엔티티의 id의 타입
//JpaRepository<Board,Integer> entity=Board entity ID Type=Integer
public interface BoardRepository extends JpaRepository<Board,Integer> {
}
//@Repository
// Entity에 의해 생성된 DB에 접근하는 메서드(ex) findAll()) 들을 사용하기 위한 인터페이스이다.
// 위에서 엔티티를 선언함으로써 데이터베이스 구조를 만들었다면, 여기에 어떤 값을 넣거나,
// 넣어진 값을 조회하는 등의 CRUD(Create, Read, Update, Delete)를 해야 쓸모가 있는데,
// 이것을 어떻게 할 것인지 정의해주는 계층이라고 생각하면 된다.