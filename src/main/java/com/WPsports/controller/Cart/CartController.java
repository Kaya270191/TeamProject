package com.WPsports.controller.Cart;

import com.WPsports.entity.Cart.Cart;
import com.WPsports.entity.Cart.CartItem;
import com.WPsports.entity.Facility;
import com.WPsports.service.Cart.CartItemService;
import com.WPsports.service.Cart.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;

    //postmapping으로 요청을 받아 member_id와 facility_id를 받음
    @PostMapping("/cart/{member_id}/{facility_id}")
    public String getCart(@PathVariable String member_id,
                        @PathVariable Long facility_id,
                        HttpServletRequest request,
                        Model model){
        HttpSession session = request.getSession();
        log.info("member_id = {}",member_id);
        log.info("facility_id={}",facility_id);

        //현재 활동중인 멤버의 cart를 가지러감
        Cart nowCart = cartService.getCart(member_id);
        log.info("nowCart={}",nowCart);

        //입력받은 facility_id로 cart item 만들러감
        CartItem newCartItem=cartItemService.createItem(nowCart.getCart_id(),facility_id);

        log.info("newCartItem={}",newCartItem);

        session.setAttribute("cart",nowCart);

        return "redirect:/facility/"+facility_id;
    }
}
