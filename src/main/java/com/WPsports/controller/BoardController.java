package com.WPsports.controller;


import com.WPsports.dto.BoardForm;
import com.WPsports.entity.Board;
import com.WPsports.repository.BoardRepository;
import com.WPsports.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class BoardController {


    @Autowired //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @GetMapping("/boards/new")//게시글 작성
    public String newBoardForm(){
        return "boards/new"; //templates/boards/new
    }

    @PostMapping("/boards/create")  //게시글 작성
    public String createBoard(BoardForm form){ //폼 데이터로 받은 것을 파라미터로 들어옴
        log.info("매빞ㅇ됨");
        log.info(newBoardForm().toString());
        //1: Dto -> Entity로 변환
        Board board = form.toEntity();
        log.info(board.toString());
        //2: Repository에 Entity DB에 저장
        Board saved = boardRepository.save(board);
        log.info(saved.toString());
        return "redirect:/boards/" + saved.getId();
    }


    @GetMapping("/boards/{id}")
    public String show(@PathVariable Long id, Model model) { //{id}로 부터 받은 것
        log.info("id = " + id);
        //1: id로 데이터를 가져옴
//    Board boardEntity = boardRepository.findById(id).orElse(null);
        Board boardEntity = boardService.show(id);
        boardService.updateView(id);
//       boardService.updateView(id);//추가
        //2: 가져온 데이터를 모델에 등록
        model.addAttribute("board", boardEntity);
        //3: 보여줄 페이지 설정
        return "boards/show";
    }

//    @GetMapping("/boards")  //게시글 리스트 보여줌
//    public String index(Model model){
//        //1: 모든 Board 가져옴
//        List<Board> BoardEntityList = boardRepository.findAll();
//        //2: 가져온 Board 묶음을 뷰로 전달
//        model.addAttribute("boardList", BoardEntityList);
//        //3: 뷰 페이지를 설정
//        return "boards/index";
//    }


    @GetMapping("/boards")  //게시글 리스트 보여줌
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        //1: 모든 Board 가져옴
//        List<Board> BoardEntityList = boardRepository.findAll();
        Page<Board> BoardEntityList = boardService.pageList(pageable);
        //2: 가져온 Board 묶음을 뷰로 전달
        model.addAttribute("boardList", BoardEntityList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", BoardEntityList.hasNext()); model.addAttribute("hasPrev", BoardEntityList.hasPrevious());

        //3: 뷰 페이지를 설정
        return "/boards/index";
    }



    @GetMapping("/boards/{id}/edit")
    public String edit(@PathVariable Long id, Model model){ //url path에서 id를 가져온다
        // 1: 수정할 데이터를 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null);
        // 2: 모델에 데이터를 등록
        model.addAttribute("board", boardEntity);
        //3: 뷰페이지 설정
        return "boards/edit"; //수정페이지를 반환
    }

    @PostMapping("/boards/update")
    public String update(BoardForm form){
        log.info(form.toString());
        //1: DTO를 앤티티로 변환
        Board boardEntity = form.toEntity(); //수정할 데이터

        //2: 앤티티-> DB저장
        //2-1: DB에서 기존 데이터 가져오기
        Board target = boardRepository.findById(boardEntity.getId()).orElse(null);

        //2-2: 기존 값 갱신
        if (target != null){
            boardRepository.save(boardEntity);
        }
        //3: 수정 결과 리다이랙트
        return "redirect:/boards/" + boardEntity.getId();
    }

    //삭제
    @GetMapping("/boards/{id}/delete") //deletemapping 이여야 하지만 getmapping 으로 받음
    public String delete(@PathVariable Long id, RedirectAttributes rttr){ //url path에서 id를 가져온다. RedirectAttributes 는 삭제시에 메시지 띄어주려고
        log.info("삭제 요청이 들어왔습니다!");

        //1: 삭제 대상을 가져온다
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2: 대상을 DB 에서 삭제한다
        if (target != null){
            boardRepository.delete(target); //리파지토리가 제공하는 delete 메소드를 사용해서 삭제
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다"); //articles 페이지에 메시지가 뜸
        }
        //3: 결과 페이지로 리다이렉트 한다
        return "redirect:/boards"; // 목록으로 리다이렉트
    }

    //검색
    @GetMapping("/boards/search")
    public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> searchList = boardService.search(keyword, pageable);
        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());

        return "/boards/search";
    }


}
