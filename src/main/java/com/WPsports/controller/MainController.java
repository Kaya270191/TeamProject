package com.WPsports.controller;



import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Facility;
import com.WPsports.entity.Member;
import com.WPsports.repository.FacilityRepository;
import com.WPsports.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private FacilityService facilityService;
    @Autowired
    private FacilityRepository facilityRepository;


    @GetMapping("/domain")
    public String domain(){
        return "mains/main";
    }

    @GetMapping("/domain/{value}")
    public  String faList(@PathVariable String value, Model model){
        //service에 위임
        List<Facility> serached = facilityService.serach(value);

        //model에 등록
        model.addAttribute("facilityList", serached);

        // 페이지 전환
       return "/mains/facilityList";
    }

    // 업체 등록 폼
    @GetMapping("/facility/new") // /facility/new
    public String newFacilityForm() {
        return "/facility/new"; //templates/facility/new.html
    }

    @PostMapping("/facility/create")
    public String createFacility(FacilityForm form){
        log.info(form.toString());
        Facility facility = form.toEntity(); // Entity로 변환
        log.info("입력받은 업체 정보: "+facility.toString());
        Facility saved = facilityRepository.save(facility); //DB에 저장
        log.info("DB 저장 전: " +saved.toString());
        return "redirect:/facility/" + saved.getId();
    }

    //업체 뷰 페이지
    @GetMapping("/facility/{id}")
    public String show(@PathVariable Long id, Model model, HttpServletRequest req){
        Facility facilityEntity = (Facility) facilityRepository.findAllById(id);

        HttpSession session= req.getSession();
        Member user = (Member) session.getAttribute("member");

        model.addAttribute("facility", facilityEntity);
        return "/facility/show";
    }

    // 장바구니에 물건 넣기
    @PostMapping("facility/cart/{id}/{facilityId}")
    public String addCartItem(@PathVariable("id") String id, @PathVariable("facilityId") Long facilityId, int count, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member user = (Member) session.getAttribute("member");
        Facility facility = facilityService.itemView(facilityId);

        log.info("user -> ", user.toString());
        log.info("fa -> ", facility.toString());

        facilityService.addCart(user, facility, count);

        return "redirect:/facility/{facilityId}";
    }

    //업체 삭제
    @GetMapping("/facility/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1: 삭제 대상을 가져온다
        Facility target = facilityRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2: 대상을 DB 에서 삭제한다
        if (target != null){
            facilityRepository.delete(target); //리파지토리가 제공하는 delete 메소드를 사용해서 삭제
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다"); //articles 페이지에 메시지가 뜸
        }
        //3: 결과 페이지로 리다이렉트 한다
        return "redirect:/main"; // 업체목록으로 리다이렉트-> 수정해야함
    }

    // 업체 수정
    @GetMapping("/facility/{id}/edit")
    public String edit(@PathVariable Long id, Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Member nowMember=(Member)session.getAttribute("member");
        log.info("현재 접근하려는 member={}",nowMember);
        log.info(nowMember.getAuth());
        if(nowMember.getAuth().equals("ADMIN")){
            // 1: 수정할 데이터를 가져오기
            Facility facilityEntity = facilityRepository.findById(id).orElse(null);
            // 2: 모델에 데이터를 등록
            model.addAttribute("facility", facilityEntity);
            //3: 뷰페이지 설정
            return "facility/edit"; //수정페이지를 반환
        }
        return"/members/admin/noAdmin";
    }

    @PostMapping("/facility/update")
    public String update(FacilityForm form){
        log.info(form.toString());
        //1: DTO를 앤티티로 변환
        Facility facilityEntity = form.toEntity(); //수정할 데이터

        //2: 앤티티-> DB저장
        //2-1: DB에서 기존 데이터 가져오기
        Facility target = facilityRepository.findById(facilityEntity.getId()).orElse(null);

        //2-2: 기존 값 갱신
        if (target != null){
            facilityRepository.save(facilityEntity);
        }
        //3: 수정 결과 리다이랙트
        return "redirect:/facility/" + facilityEntity.getId();
    }
}
