package com.WPsports.repository;

import com.WPsports.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    Object findAllById(Long id);

    @Override
    ArrayList<Facility> findAll();

}
