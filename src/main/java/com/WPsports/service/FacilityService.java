package com.WPsports.service;

import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Cart;
import com.WPsports.entity.CartItem;
import com.WPsports.entity.Facility;
import com.WPsports.entity.Member;
import com.WPsports.repository.CartItemRepository;
import com.WPsports.repository.CartRepository;
import com.WPsports.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;


    public ArrayList<Facility> serach(String value) {
        ArrayList<Facility> serached = facilityRepository.findByCategory(value);
        return serached;
    }

    public Facility itemView(Long facilityId) {
         Facility fined = facilityRepository.findById(facilityId).orElse(null);
         return fined;
    }

    public void addCart(Member user, Facility newfacility, int count) {
        // 유저 id로 해당 유저의 장바구니 찾기
        Cart cart = cartRepository.findByUserId(user.getId());

        // 장바구니가 존재하지 않는다면
        if (cart == null) {
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        Facility facility = facilityRepository.findFacilityById(newfacility.getId());
        CartItem cartItem = cartItemRepository.findByCartIdAndFacilityId(cart.getId(), facility.getId());

        // 상품이 장바구니에 존재하지 않는다면 카트상품 생성 후 추가
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, facility, count);
            cartItemRepository.save(cartItem);
        }

        // 상품이 장바구니에 이미 존재한다면 수량만 증가
        else {
//            CartItem update = cartItem;
//            update.setCart(cartItem.getCart());
//            update.setItem(cartItem.getItem());
//            update.addCount(amount);
//            update.setCount(update.getCount());
//            cartItemRepository.save(update);
        }

        // 카트 상품 총 개수 증가
        cart.setCount(cart.getCount()+count);
    }
}
