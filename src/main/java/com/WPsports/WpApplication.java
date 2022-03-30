package com.WPsports;

import com.WPsports.controller.MemberController;
import com.WPsports.dto.MemberForm;
import com.WPsports.entity.Member;
import com.WPsports.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WpApplication {
	@Autowired
	MemberService memberService;

	public static void main(String[] args) {
		SpringApplication.run(WpApplication.class, args);
	}

//	서버 실행시 test,1234 로 로그인 가능
	@Bean
	public CommandLineRunner testMember() throws Exception{
		return (String[] args)->{

			MemberForm testMember=new MemberForm(
					"test",
					"1234",
					"현호",
					"01012341234",
					"112233",
					"22@test.com",
					"대구");

			memberService.save(testMember);
		};
	}
}
