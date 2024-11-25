package com.wtm.spring_boot_wtm.controller;
import com.wtm.spring_boot_wtm.model.Bar;
import com.wtm.spring_boot_wtm.model.Favorite;
import com.wtm.spring_boot_wtm.model.FavoriteRequest;
import com.wtm.spring_boot_wtm.repository.BarRepository;
import com.wtm.spring_boot_wtm.repository.IFavoriteRepository;
import com.wtm.spring_boot_wtm.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {


    @Autowired
    private IFavoriteRepository favoritesRepository;

    @Autowired
    private BarRepository barsRepository;

    @Autowired
    private FavoriteService favoriteService;  

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteRequest request) {
        Bar bar = barsRepository.findByPlaceId(request.getPlaceId());
        if (bar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bar not found");
        }
        //Check for past favorites
        if (favoritesRepository.existsByUserIdAndBarsId(request.getUserId(), bar.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Favorite already exists");
        }
    
        //Create Favorite
        Favorite favorite = new Favorite();
        favorite.setUserId(request.getUserId());
        favorite.setBarsId(bar.getId());
        favoritesRepository.save(favorite);
    
        return ResponseEntity.ok("Favorite added successfully");
    }
    

    // @DeleteMapping
    // public ResponseEntity<Void> removeFavorite(@RequestParam String userId, @RequestParam String placeId) {
    //     try {
    //         favoriteService.removeFavorite(userId, placeId);
    //         return ResponseEntity.noContent().build();
    //     } catch (IllegalArgumentException e) {
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    @GetMapping("/list")
    public ResponseEntity<List<Long>> listFavorites(@RequestParam String userId) {
        List<Long> favorites = favoriteService.getFavoriteBarsIdsByUser(userId);
        return ResponseEntity.ok(favorites);
    }
    
    
}
