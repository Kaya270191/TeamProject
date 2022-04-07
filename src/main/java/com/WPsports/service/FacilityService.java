package com.WPsports.service;

import com.WPsports.entity.Facility;
import com.WPsports.repository.Cart.CartItemRepository;
import com.WPsports.repository.Cart.CartRepository;
import com.WPsports.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;


    public ArrayList<Facility> serach(String value) {
        ArrayList<Facility> serached = facilityRepository.findByCategory(value);
        return serached;
    }

    public Facility itemView(Long facilityId) {
         Facility fined = facilityRepository.findById(facilityId).orElse(null);
         return fined;
    }

}
