package com.WPsports.service;

import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Facility;
import com.WPsports.entity.Member;
import com.WPsports.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;


    public ArrayList<Facility> serach(String value) {
        ArrayList<Facility> serached = facilityRepository.findByCategory(value);
        return serached;
    }

    public Facility itemView(Long facilityId) {
         Facility fined = facilityRepository.findById(facilityId).orElse(null);
         return fined;
    }

    public void addCart(Member user, Facility facility, int count) {
    }
}
