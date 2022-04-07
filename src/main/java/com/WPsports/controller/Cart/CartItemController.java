package com.WPsports.controller.Cart;


import com.WPsports.entity.Cart.CartItem;
import com.WPsports.entity.Member;
import com.WPsports.service.Cart.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class CartItemController {
    @Autowired
    CartItemService cartItemService;
}
