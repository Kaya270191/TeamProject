package com.WPsports.repository.Cart;

import com.WPsports.entity.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
