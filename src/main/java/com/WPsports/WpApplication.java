package com.WPsports;

import com.WPsports.controller.MemberController;
import com.WPsports.dto.MemberForm;
import com.WPsports.entity.Member;
import com.WPsports.security.loginFilter;
import com.WPsports.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Filter;

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
					"대구",
					"MEMBER");

			MemberForm testAdmin=new MemberForm(
					"admin",
					"1234",
					"관리자",
					"01043214321",
					"112233",
					"33@test.com",
					"대구",
					"ADMIN");

			memberService.save(testMember);
			memberService.save(testAdmin);
		};
	}

	//	서버에 필터적용
	@Bean
	public FilterRegistrationBean setFilterRegistration(){

		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new loginFilter());
		filterRegistrationBean.addUrlPatterns("/main/*","/boards/*","/profile/*"); // string 여러개(접근제한 url)를 가변인자로 받는 메소드
		return filterRegistrationBean;
	}
}
