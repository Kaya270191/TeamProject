package com.WPsports.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; //가게 이름

    @Column
    private String tel; // 전화번호

    @Column
    private String address; //주소

    @Column
    private String operatingTime; //운영시간

    @Column
    private String starRate; //별점

    @Column
    private String photo; //사진

    @Column
    private String category;



}
