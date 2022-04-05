package com.WPsports.dto;

import com.WPsports.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm{

    @NotBlank(message = "아이디에 공백은 포함할수 없습니다.")
    @Size(min=2,max=16,message = "아이디는 2~16자리만 입력 가능합니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @NotBlank(message = "비밀번호에 공백은 포함할수 없습니다.")
    private String pw;

    @Nullable
    @NotBlank(message = "이름에 공백은 포함할수 없습니다.")
    @Size(min=2,max=6,message = "이름은 2~8자리 까지 입력 가능합니다.")
    @Pattern(regexp = "^[a-zA-Zㄱ-힣]*$",message = "이름에 숫자와 특수문자는 포함될 수 없습니다.")
    private String name;

    @Pattern(regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message = "휴대폰번호의 형식이 맞지 않습니다.")
    private String phone;

    @Nullable
    private String birthday;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    private String address;

    private String auth;

    public Member toEntity(){
        return Member.builder()
                .id(this.getId())
                .pw(this.getPw())
                .name(this.getName())
                .phone(this.getPhone())
                .birthday(this.getBirthday())
                .email(this.getEmail())
                .address(this.getAddress())
                .auth(this.getAuth())
                .build();
    }
}