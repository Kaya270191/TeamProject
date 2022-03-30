package com.WPsports.boardComment;

import com.WPsports.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 여러 댓글이 하나의 Board 에 연결
    @JoinColumn(name = "board_id")
    private Board board; //댓글의 부모 게시글

    @Column
    private String nickname;

    @Column
    private String body; //댓글 내용

    public static Comment createComment(CommentDto dto, Board board) {
        // 예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getBoardId() != board.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 없어야 합니다");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                board,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id입니다");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
