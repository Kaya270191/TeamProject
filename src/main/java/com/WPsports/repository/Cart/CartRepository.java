package com.WPsports.repository.Cart;

import com.WPsports.entity.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Override
    List<Cart> findAll();
}
