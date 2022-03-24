package com.WPsports.dto;

import com.WPsports.entity.Board;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class BoardForm {

    private Long id; //게시글 번호

    private String title; //제목

    private String writer; //작성자

    private String content; //내용
    private int view_count; //조회수
    private String reg_date; //게시글 작성일자

    public Board toEntity() {
        return new Board(id, title, writer, content, view_count, reg_date);
    }
}
