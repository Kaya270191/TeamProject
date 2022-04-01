package com.WPsports.dto;

import com.WPsports.entity.Facility;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
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
        return Facility.builder()
                .id(this.getId())
                .name(this.getName())
                .tel(this.getTel())
                .address(this.getAddress())
                .operatingTime(this.getOperatingTime())
                .category(this.getCategory())
                .starRate(this.getStarRate()).build();
    }
}
