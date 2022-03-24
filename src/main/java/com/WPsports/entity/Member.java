package com.WPsports.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member {

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
}
