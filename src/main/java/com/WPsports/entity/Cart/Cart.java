package com.WPsports.entity.Cart;


import com.WPsports.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@ToString
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @OneToOne
    @JoinColumn(name="member_key")
    private Member member;

}
