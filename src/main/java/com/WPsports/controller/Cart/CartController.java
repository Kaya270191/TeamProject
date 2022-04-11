package com.WPsports.controller.Cart;

import com.WPsports.entity.Cart.Cart;
import com.WPsports.entity.Cart.CartItem;
import com.WPsports.entity.Facility;
import com.WPsports.entity.Member;
import com.WPsports.service.Cart.CartItemService;
import com.WPsports.service.Cart.CartService;
import com.WPsports.service.FacilityService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    FacilityService facilityService;

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

    //postmapping으로 요청을 받아 member_id와 facility_id를 받음
    @PostMapping("/cart/{member_id}/{facility_id}/cancel")
    public String cancelItem(@PathVariable  String member_id,
                             @PathVariable Long facility_id,
                             HttpServletRequest request,
                             Model model){
        HttpSession session = request.getSession();
        log.info("member_id = {}",member_id);
        log.info("facility_id={}",facility_id);

        //현재 활동중인 멤버의 cart를 가지러감
        Cart nowCart = cartService.getCart(member_id);
        log.info("nowCart={}",nowCart);

        //입력받은 facility_id와 cart_id 로 cart item 지우러감
        cartItemService.removeItem(nowCart.getCart_id(),facility_id);

        return  "redirect:/facility/"+facility_id;
    }

//    찜목록 페이지
    @GetMapping("/cart/bookedList")
    public String bookedList(HttpServletRequest request){
        HttpSession session=request.getSession();
        Member nowMember = (Member) session.getAttribute("member");
        Cart nowCart = cartService.getCart(nowMember.getId());
        List<CartItem> allList = cartItemService.getItems();
        List<Facility> bookedList = new ArrayList<Facility>();
        for(CartItem item:allList){
            if(item.getCart().getCart_id().equals(nowCart.getCart_id())){
                bookedList.add(item.getFacility());
            }
        }
        if(bookedList.size()!=0){
            session.setAttribute("bookedList",bookedList);
            session.removeAttribute("noList");
            log.info(bookedList.toString());
        }else{
            session.setAttribute("noList","등록된 업체가 없습니다.");
            session.removeAttribute("bookedList");
        }


        return "/members/bookedList";
    }
}
