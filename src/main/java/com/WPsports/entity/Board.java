package com.WPsports.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //게시글 아이디

    @Column(length = 500, nullable = false)
    private String title; //제목

    @Column(nullable = false)
    private String writer; //작성자

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; //내용

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view_count; //조회수

    @CreatedDate
    private String reg_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")); //게시글 작성일자


    public void patch(Board board) {
        if(board.title != null)
            this.title = board.title;
        if(board.content != null)
            this.content = board.content;
    }

}
