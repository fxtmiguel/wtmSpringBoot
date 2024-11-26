package com.wtm.spring_boot_wtm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wtm.spring_boot_wtm.model.Favorite;



@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserIdAndBarsId(String userId, Long barsId);

    List<Favorite> findByUserId(String userId);
    
    boolean existsByUserIdAndBarsId(String userId, Long barsId);

   
    
}
