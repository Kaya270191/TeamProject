package com.WPsports.service;


import com.WPsports.dto.BoardForm;
import com.WPsports.entity.Board;
import com.WPsports.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired //DI
    private BoardRepository boardRepository;

    public List<Board> index() {
        return boardRepository.findAll();
    }

    public Board show(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board create(BoardForm dto) {
        Board board = dto.toEntity();
        if(board.getId() != null){
            return null;
        }
        return boardRepository.save(board);
    }

    public Board update(Long id, BoardForm dto) {
        Board board = dto.toEntity();
        Board target = boardRepository.findById(id).orElse(null);
        if(target == null || id != board.getId()){
            log.info("잘못된 요청 id {}, board: {}", id, board.toString());
            return null;
        }
        target.patch(board);
        Board updated = boardRepository.save(target);
        return updated;
    }

    public Board delete(Long id) {
        Board target = boardRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }
        boardRepository.delete(target);
        return target;
    }

    @Transactional //조회수 증가
    public int updateView(Long id){
        return boardRepository.updateView(id);
    }

    @Transactional //페이지 처리
    public Page<Board> pageList(Pageable pageable){
        return boardRepository.findAll(pageable);

    }

    @Transactional //검색
    public Page<Board> search(String keyword, Pageable pageable){
        Page<Board> boardList = boardRepository.findByTitleContaining(keyword, pageable);
        return boardList;
    }




}
