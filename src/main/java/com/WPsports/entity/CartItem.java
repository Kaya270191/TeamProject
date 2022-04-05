package com.WPsports.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facility;

    public static CartItem createCartItem(Cart cart, Facility facility){
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setFacility(facility);
        return cartItem;
    }



}
