package com.WPsports.repository.Cart;

import com.WPsports.entity.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Override
    List<CartItem> findAll();
}
