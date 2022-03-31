package com.WPsports.controller;


import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Facility;
import com.WPsports.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private FacilityRepository facilityRepository;

    // 업체 등록 폼
    @GetMapping("/facility/new") // /facility/new
    public String newFacilityForm() {
        return "/facility/new"; //templates/facility/new.html
    }

    @PostMapping("/facility/create")
    public String createFacility(FacilityForm form){
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



}
