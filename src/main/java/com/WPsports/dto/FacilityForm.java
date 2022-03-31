package com.WPsports.dto;

import com.WPsports.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class FacilityForm {

    private Long id;

    private String name; //가게 이름
    private String tel; // 전화번호
    private String address; //주소
    private String operatingTime; //운영시간
    private String photo; //사진
    private String category;
    private String starRate;


    public Facility toEntity() {
        return new Facility(id, name, tel, address, operatingTime, photo, category, starRate);
    }
}
