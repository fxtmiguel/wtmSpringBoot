package com.wtm.spring_boot_wtm.service;

import com.wtm.spring_boot_wtm.model.Favorite;
import com.wtm.spring_boot_wtm.repository.IFavoriteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteService {

    
    private IFavoriteRepository favoriteRepository;

    FavoriteService(IFavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void addFavorite(String userId, Long barsId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setBarsId(barsId);

        favoriteRepository.save(favorite);
    }
    
    public List<Long> getFavoriteBarsIdsByUser(String userId) {
        return favoriteRepository.findByUserId(userId).stream()
                .map(Favorite::getBarsId)
                .toList();
    }

}
