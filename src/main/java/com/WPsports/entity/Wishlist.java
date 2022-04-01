package com.WPsports.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)// 여러개 찜리스트가 하나의 Member에 연결
    @JoinColumn(name = "member_key")
    private Member member; //멤버

    @OneToMany
    @JoinColumn(name = "facility_id")
    private List<Facility> facility = new ArrayList<>();


}
