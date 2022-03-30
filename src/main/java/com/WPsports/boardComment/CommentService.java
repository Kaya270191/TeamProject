package com.WPsports.boardComment;

import com.WPsports.entity.Board;
import com.WPsports.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    //조회
    public List<CommentDto> comments(Long boardId){
        List<Comment> comments = commentRepository.findByBoardId(boardId);

        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i=0; i< comments.size(); i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
            log.info(dto.getId().toString());
        }return dtos;
    }

    //댓글 생성
    @Transactional
    public CommentDto create(Long boardId, CommentDto dto){
        // 게시글 조회 및 예외 발생
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패, 대상 게시글이 없습니다")); //orElseThrow 만약에 없다면 예외를 발생시킴

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, board);

        // 댓글 엔티티를 DB로저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    //댓글 수정
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        log.info("댓수정 서비스");
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱신
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    //댓글 삭제
    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 (및 예외 발생)
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다"));
        // DB에서 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
