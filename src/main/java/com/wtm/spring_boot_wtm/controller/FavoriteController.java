package com.wtm.spring_boot_wtm.controller;
import com.wtm.spring_boot_wtm.model.Bar;
import com.wtm.spring_boot_wtm.model.Favorite;
import com.wtm.spring_boot_wtm.model.FavoriteRequest;
import com.wtm.spring_boot_wtm.repository.BarRepository;
import com.wtm.spring_boot_wtm.repository.IFavoriteRepository;
import com.wtm.spring_boot_wtm.service.FavoriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private IFavoriteRepository favoritesRepository;

    private BarRepository barsRepository;

    private FavoriteService favoriteService;  

    public FavoriteController(FavoriteService favoriteService, BarRepository barsRepository, IFavoriteRepository favoritesRepository){
        this.favoriteService = favoriteService;
        this.barsRepository = barsRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFavorite(@RequestBody FavoriteRequest request) {
        Optional<Bar> optionalBar = barsRepository.findByPlaceId(request.getPlaceId());

        if (optionalBar.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bar not found");
        }
    
        Bar bar = optionalBar.get();
        if (favoritesRepository.existsByUserIdAndBarsId(request.getUserId(), bar.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Favorite already exists");
        }
    
        Favorite favorite = new Favorite();
        favorite.setUserId(request.getUserId());
        favorite.setBarsId(bar.getId());
        favoritesRepository.save(favorite);
    
        return ResponseEntity.ok("Favorite added successfully");
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<Long>> listFavorites(@RequestParam String userId) {
        List<Long> favorites = favoriteService.getFavoriteBarsIdsByUser(userId);
        return ResponseEntity.ok(favorites);
    }
    
    
}
