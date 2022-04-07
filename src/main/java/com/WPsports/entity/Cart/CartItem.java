package com.WPsports.entity.Cart;


import com.WPsports.entity.Facility;
import com.WPsports.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;

    @OneToOne
    @JoinColumn(name="id")
    private Facility facility;
}
