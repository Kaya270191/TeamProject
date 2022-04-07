package com.WPsports.dto.Cart;

import com.WPsports.entity.Cart.Cart;
import com.WPsports.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartForm {

    @Autowired
    MemberRepository memberRepository;

    private Long member_key;

    //CartForm이 가지고있는 member_key로 Cart Entity생성
    public Cart toEntity(){
        return Cart.builder()
                .member(memberRepository.getById(this.member_key))
                .build();
    }
}
