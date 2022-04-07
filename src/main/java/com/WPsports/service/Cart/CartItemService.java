package com.WPsports.service.Cart;

import com.WPsports.entity.Cart.CartItem;
import com.WPsports.repository.Cart.CartItemRepository;
import com.WPsports.repository.Cart.CartRepository;
import com.WPsports.repository.FacilityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    FacilityRepository facilityRepository;

    //입력받은 cart_id와 facility_id로 cart_Item 생성
    public CartItem createItem(Long cart_id,Long facility_id){
        CartItem newCartItem=CartItem.builder()
                .cart(cartRepository.getById(cart_id))
                .facility(facilityRepository.getById(facility_id))
                .build();
        //저장해줘야 cartItem의 id가 생김
        cartItemRepository.save(newCartItem);
        return newCartItem;
    }

}
