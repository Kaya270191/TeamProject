package com.WPsports.repository;

import com.WPsports.entity.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    Object findAllById(Long id);


//    Optional<Board> findById(@Param("id") Long id);

//    @Query("select b from Board b where b.id = :id") //오류나서 추가해봄
//    Optional<Board> findById(@Param("id") Long id);

    @Override
    ArrayList<Board> findAll();

    @Modifying
    @Query("update Board b set b.view_count = b.view_count + 1 where b.id =:id")
    int updateView(@Param(value="id") Long id);

    //검색
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);


}
