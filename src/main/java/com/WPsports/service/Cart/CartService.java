package com.WPsports.service.Cart;


import com.WPsports.entity.Cart.Cart;
import com.WPsports.entity.Cart.CartItem;
import com.WPsports.repository.Cart.CartRepository;
import com.WPsports.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class CartService {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    CartRepository cartRepository;

    //member_id를 받아 cart를 찾음
    public Cart getCart(String member_id){
        //실력부족으로 일단 모든 카트 들고옴
        List<Cart> allCart =cartRepository.findAll();

        //모든카트 for문 돌려서
        for(Cart cart:allCart){
            //들고온 모든 카트의 member의 member_key와 현재 활동중인 member의 member_key를 비교
            if(cart.getMember().getMember_key().equals(memberRepository.getById(member_id).getMember_key())){
                //있다면 존재하는 cart를 돌려줌
                log.info("CartService/cart={}",cart.toString());
                return cart;
            }
        }
        //없다면 새로운 카트를 만들어 돌려줌
        Cart newCart = Cart.builder()
                .member(memberRepository.getById(member_id))
                .build();
        //save해줘야 cart의 id가 생김
        cartRepository.save(newCart);
        log.info("CartService/newCart={}",newCart.toString());
        return newCart;
    }

}
