package com.WPsports.boardComment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long boardId){
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(boardId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long boardId, @RequestBody CommentDto dto){

        //서비스에게 위임
        CommentDto createdDto = commentService.create(boardId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    @RequestMapping(value={"/api/comments/{id}"}, method = RequestMethod.PATCH)
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        //서비스에게 위임
        log.info("코멘트 수정중");
        CommentDto updatedDto = commentService.update(id, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }


    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){

        //서비스에게 위임
        CommentDto updatedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
}
