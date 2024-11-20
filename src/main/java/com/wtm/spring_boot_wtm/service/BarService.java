package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Bar;
import com.wtm.spring_boot_wtm.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public boolean updateBusyness(String placeId, String busyness) {
        Optional<Bar> optionalBar = barRepository.findByPlaceId(placeId);

        if (optionalBar.isPresent()) {
            Bar bar = optionalBar.get();
            bar.setBusyness(Integer.parseInt(busyness)); // Update the busyness field
            barRepository.save(bar); // Save the updated entity
            return true;
        }

        return false; // If the bar with the specified placeId doesn't exist
    }
}
