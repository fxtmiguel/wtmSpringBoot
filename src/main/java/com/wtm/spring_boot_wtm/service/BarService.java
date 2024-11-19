package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Bar;
import com.wtm.spring_boot_wtm.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarService {

    private final BarRepository barRepository;

    @Autowired
    public BarService(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    public void addBarIfNotExists(String placeId, String name) {
        if (!barRepository.existsByPlaceId(placeId)) {
            Bar bar = new Bar();
            bar.setPlaceId(placeId);
            bar.setName(name); // Optional
            barRepository.save(bar);
        }
    }
}
