package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Favorite;
import com.wtm.spring_boot_wtm.repository.IFavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    @Autowired
    private IFavoriteRepository favoriteRepository;

    public void addFavorite(String userId, Long barsId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setBarsId(barsId);

        favoriteRepository.save(favorite);
    }

    public void removeFavorite(String userId, Long barsId) {
        Favorite favorite = favoriteRepository.findByUserIdAndBarsId(userId, barsId)
                .orElseThrow(() -> new IllegalArgumentException("Favorite not found"));
    
        favoriteRepository.delete(favorite);
    }
    
    public List<Long> getFavoriteBarsIdsByUser(String userId) {
        return favoriteRepository.findByUserId(userId).stream()
                .map(Favorite::getBarsId)
                .collect(Collectors.toList());
    }

}
