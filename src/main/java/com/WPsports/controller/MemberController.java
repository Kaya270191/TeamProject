package com.WPsports.controller;


import com.WPsports.boardComment.CommentDto;
import com.WPsports.entity.Member;
import com.WPsports.dto.MemberForm;
import com.WPsports.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class MemberController {
    private String CLIENT_ID = "lcmV4g_oGg3Q5ccJbUK1"; //애플리케이션 클라이언트 아이디값";

    @Autowired
    MemberService memberService;

    //    초기화면
    @GetMapping(value = "/")
    public String signupForm(Model model,HttpSession session)throws UnsupportedEncodingException, UnknownHostException{
        //naver login을 위한 설정
        String redirectURI = URLEncoder.encode("http://localhost:5555/naver/callback", "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s",
                CLIENT_ID, redirectURI, state);
        session.setAttribute("state", state);
        model.addAttribute("apiURL", apiURL);
        return "members/signup";
    }

    //session 에 member 인증이 안됐을경우 보낼 페이지
    @GetMapping(value="/nomember")
    public String noMember(){
        return "members/noMember";
    }

    @GetMapping("/main")
    public String mainUP(){
        return "mains/main";
    }


    //    회원가입
    @PostMapping("/signup")
    public String saveMember(@Valid MemberForm memberForm, Errors errors, Model model) {
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
        HttpSession session = req.getSession();
        session.invalidate();
        log.info("id={},pw={}",id,pw);
        if(memberService.loginDO(id,pw,model)){
            session = req.getSession();
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
    public String goProfile(HttpServletRequest req){
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
        return "members/profile/profile";
    }

    @PostMapping("/profile/checkPw")
    @ResponseBody
    public int checkPw(String id,String pw,Model model){
        log.info("id={}, pw={}",id,pw);
        if(memberService.loginDO(id,pw,model)){
            return 1;
        };
        return 0;
    }

    //profile 편집
    @GetMapping("/profile/edit")
    public String edit(HttpServletRequest request){
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpSession session=httpServletRequest.getSession(false);
        log.info("session.id={}",session.getAttribute("id"));
        return "members/profile/edit";
    }

    @GetMapping("/members/memberList")
    public String hello(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Member nowMember=(Member)session.getAttribute("member");
        log.info("현재 접근하려는 member={}",nowMember);
        log.info(nowMember.getAuth());
        if(nowMember.getAuth().equals("ADMIN")){
            List<Member> allMember= memberService.allMember();
            model.addAttribute("memberList",allMember);
            return "/members/admin/allmember";
        }
        return"/members/admin/noAdmin";
    }

    @PatchMapping("/profile/edit/{id}")
    @ResponseBody
    public int memberEdit(@PathVariable String id,@RequestBody MemberForm memberForm,HttpServletRequest request){
        HttpSession session= request.getSession();
        log.info("id={}",id);
        log.info("ControllerMember={}",memberForm.toString());
        Member updateMember=memberService.memberEdit(id,memberForm);
        log.info("updateMember={}",updateMember.toString());
        session.setAttribute("member",updateMember);
        log.info("id={}",session.getAttribute("member").toString());
        return 1;
    }

    @PostMapping("/profile/outCheck/{id}")
    @ResponseBody
    public int memberCheck(@PathVariable String id,String pw){
        if(memberService.memberCheck(id,pw)){
            return 1;
        }
        return 0;
    }

    @PostMapping("/profile/signOut/{id}")
    @ResponseBody
    public int signOut(@PathVariable String id,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        memberService.memberOut(id);
        return 1;
    }

    @PostMapping("/admin/memberOut/{id}")
    @ResponseBody
    public int memberOut(@PathVariable String id){
        memberService.memberOut(id);
        return 1;
    }

    @GetMapping("/thankUbye")
    public String byebye(){
        return "/members/thankUbye";
    }


}

