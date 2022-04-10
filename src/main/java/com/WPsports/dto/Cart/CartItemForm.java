package com.WPsports.dto.Cart;

import com.WPsports.entity.Cart.CartItem;
import com.WPsports.repository.Cart.CartRepository;
import com.WPsports.repository.FacilityRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItemForm {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    FacilityRepository facilityRepository;

    @JsonProperty("cart_id")
    private Long cart_id;

    @JsonProperty("id")
    private Long facility_id;

    public CartItem toEntity(){
        return CartItem.builder()
                .cart(cartRepository.getById(cart_id))
                .facility(facilityRepository.getById(facility_id))
                .build();
    }
}
