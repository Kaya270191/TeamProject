package com.WPsports.boardComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select * from comment where board_id = :boardId", nativeQuery = true)
    List<Comment> findByBoardId(@Param(value = "boardId") Long boardId);

    @Query(value = "select * from comment where nickname = :nickname ", nativeQuery = true)
    List<Comment> findByNickname(String nickname);

    @Query(value = "select * from comment where id = :id", nativeQuery = true)
    Optional<Comment> findById(@Param(value = "id") Long id);


}
