package com.WPsports.controller;


import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Board;
import com.WPsports.entity.Facility;
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

import javax.servlet.http.HttpServletRequest;
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
    public String show(@PathVariable Long id, Model model){
        Facility facilityEntity = (Facility) facilityRepository.findAllById(id);

        model.addAttribute("facility", facilityEntity);
        return "/facility/show";
    }

    //삭제
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

}
