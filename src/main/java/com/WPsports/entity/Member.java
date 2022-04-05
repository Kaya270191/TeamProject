package com.WPsports.entity;


import com.WPsports.boardComment.CommentDto;
import com.WPsports.dto.MemberForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long member_key;

    private String id;
    private String pw;
    private String name;
    private String birthday;
    private String phone;
    private String email;
    private String address;
    private String auth;


    public void update(MemberForm memberForm){
        this.pw=memberForm.getPw();
        this.name=memberForm.getName();
        this.birthday=memberForm.getBirthday();
        this.phone=memberForm.getPhone();
        this.email=memberForm.getEmail();
        this.address=memberForm.getAddress();
    }

    @Override
    public String toString(){
        return "id : "+this.getId()+", "
                +"name : "+this.getName();
    }

}
