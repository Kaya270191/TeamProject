package com.WPsports.service;

import com.WPsports.dto.FacilityForm;
import com.WPsports.entity.Facility;
import com.WPsports.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;


    public ArrayList<Facility> serach(String value) {
        ArrayList<Facility> serached = facilityRepository.findByCategory(value);
        return serached;
    }
}
