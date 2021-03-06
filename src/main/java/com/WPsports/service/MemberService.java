package com.WPsports.service;

import com.WPsports.boardComment.Comment;
import com.WPsports.boardComment.CommentDto;
import com.WPsports.entity.Member;
import com.WPsports.dto.MemberForm;
import com.WPsports.repository.Cart.CartRepository;
import com.WPsports.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member save(MemberForm memberform){
        String pw=passwordEncoder.encode(memberform.getPw());
        MemberForm newMember=new MemberForm(
                memberform.getId(),
                pw,
                memberform.getName(),
                memberform.getPhone(),
                memberform.getBirthday(),
                memberform.getEmail(),
                memberform.getAddress(),
                memberform.getAuth()
        );
        return memberRepository.save(newMember.toEntity());
    }

    public Map<String,String> validateHandling(Errors errors){
        Map<String,String> validatorResult = new HashMap<>();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName=String.format("valid_%s",error.getField());
            validatorResult.put(validKeyName,error.getDefaultMessage());
        }
        return validatorResult;
    }

    public Boolean checkId(String id){
        log.info(id);
        Member oldMember=memberRepository.getById(id);
        if(oldMember!=null){
            return false;
        }
        return true;
    }

    public Boolean loginDO(String id,String pw,Model model) {
        Map<String, String> failed = new HashMap<>();
        Member oldMember = memberRepository.getById(id);
        if (oldMember != null) {
            if(passwordEncoder.matches(pw,oldMember.getPw())){
                model.addAttribute("member", oldMember);
                return true;
            } else {
                failed.put("loginFailed", "??????????????? ?????? ????????????.");
                model.addAttribute("loginFailed", failed.get("loginFailed"));
                return false;
            }
        } else {
            failed.put("loginFailed", "???????????? ???????????? ????????????.");
            model.addAttribute("loginFailed", failed.get("loginFailed"));
            return false;
        }
    }

    public Member getById(String id){
        return memberRepository.getById(id);
    }

    public List<Member> allMember(){
        return memberRepository.selectAllSQL();
    }
    
    //????????? ?????? ??????
    @Transactional
    public Member memberEdit(String id,MemberForm memberForm){
        Member oldMember=memberRepository.getById(id);
        if(memberForm.getPw()!=""&&memberForm.getPw()!=null){
            memberForm.setPw(passwordEncoder.encode(memberForm.getPw()));
        }else {
            memberForm.setPw(oldMember.getPw());
        }
        oldMember.update(memberForm);
        memberRepository.save(oldMember);
        Member updateMember=memberRepository.getById(id);
        return updateMember;
    };

    public Boolean memberCheck(String id,String pw){
        Member oldMember=getById(id);
        if(passwordEncoder.matches(pw,oldMember.getPw())){
            return true;
        }
        return false;
    };

//????????????
@Transactional
public Boolean memberOut(String id){
    Member oldMember= memberRepository.getById(id);
    memberRepository.delete(oldMember);
    log.info("?????? ?????? ??????");
    return true;
    }
}

