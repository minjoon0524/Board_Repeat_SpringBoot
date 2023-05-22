package com.study.board_repeat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //엔티티는 아래 클래스가 DB에 있는 테이블을 의미한다는걸 의미
@Data //@Data 어노테이션을 사용해 데이터를 보낼 수 있음
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;
}
