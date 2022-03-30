package com.WPsports.controller;


import com.WPsports.entity.Member;
import com.WPsports.dto.MemberForm;
import com.WPsports.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;


@Slf4j
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;
    
//    초기화면
    @GetMapping(value = "/")
    public String signupForm(){
        return "members/signup";
    }

    @GetMapping(value = {"/boards/**","/mains/**"})
    public String memberCheck(HttpServletRequest req){
        HttpSession session = req.getSession();
        if(session.getAttribute("member")==null){
            return "members/noMember";
        }
        else {
            return "redirect:";
        }
    }

    @GetMapping("/main")
    public String mainUP(){
        return "mains/main";
    }


//    회원가입
    @PostMapping("/signup")
    public String saveMember(@Valid MemberForm memberForm, Errors errors, Model model){
        if(errors.hasErrors()){
//            회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("memberForm",memberForm);
//            유효성 통과 못한 필드와 메세지를 핸들링
            Map<String,String> validatorResult = memberService.validateHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key,validatorResult.get(key));
            }
//            회원가입 페이지로 리턴
            return "members/signup";
        }
        model.addAttribute("MemberForm",memberForm);
        Member saveMember=memberService.save(memberForm);
        log.info("Entity id={} name={}",saveMember.getId(),saveMember.getName());
        return "members/welcomemember";
    }

//    가입 전 아이디(중복,길이) 확인
    @PostMapping("/signup/checkID")
    @ResponseBody
    public int checkId(String id){
        if(id.length()==0){
            return 3;
        }
        if(id.length()<2) {
            return 2;
        }
        if(memberService.checkId(id)) {
            return 1;
        }else{
            return 0;
        }
    }


    @GetMapping("/login")
    public String loginForm(){
        return "members/login";
    }

//        로그인
    @PostMapping("/login-do")
    public String loginDO(String id, String pw, Model model, HttpServletRequest req){
        log.info("id={},pw={}",id,pw);
        HttpSession session = req.getSession();
        if(memberService.loginDO(id,pw,model)){
            session.setAttribute("member",model.getAttribute("member"));
            return "redirect:/main";
        }else {
            return "members/login";
        }

    }

//    로그아웃
    @GetMapping("/logout")
    public String logoutDO(HttpSession session){
        System.out.println("로그아웃!");
        session.invalidate();
        return "redirect:/";
    }

//    프로필
    @GetMapping("/profile")
    public String goProfile(HttpServletRequest req,Model model){
        HttpSession session= req.getSession();
        log.info("Member={}",session.getAttribute("member"));

        Member nowMember =(Member) session.getAttribute("member");
        session.setAttribute("id",nowMember.getId());
        session.setAttribute("name",nowMember.getName());
        session.setAttribute("phone",nowMember.getPhone());
        session.setAttribute("email",nowMember.getEmail());
        session.setAttribute("birthday",nowMember.getBirthday());
        session.setAttribute("address",nowMember.getAddress());
        log.info("id={}",session.getAttribute("id"));
        return "members/profile";
    }

}

