package com.wtm.spring_boot_wtm.repository;

import com.wtm.spring_boot_wtm.model.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarRepository extends JpaRepository<Bar, Long> {
    boolean existsByPlaceId(String placeId);

    
    Optional<Bar> findByPlaceId(String placeId); 
}
