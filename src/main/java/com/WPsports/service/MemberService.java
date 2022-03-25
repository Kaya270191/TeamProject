package com.WPsports.service;

import com.WPsports.entity.Member;
import com.WPsports.dto.MemberForm;
import com.WPsports.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
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
                memberform.getAddress()
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
                failed.put("loginFailed", "비밀번호가 맞지 않습니다.");
                model.addAttribute("loginFailed", failed.get("loginFailed"));
                return false;
            }
        } else {
            failed.put("loginFailed", "아이디가 존재하지 않습니다.");
            model.addAttribute("loginFailed", failed.get("loginFailed"));
            return false;
        }
    }
}

