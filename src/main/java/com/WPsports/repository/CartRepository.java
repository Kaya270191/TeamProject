package com.WPsports.repository;

import com.WPsports.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUserId(String id);
}
