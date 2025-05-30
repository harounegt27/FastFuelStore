package com.pfe.ffs.controller.client;

import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.WishlistDto;
import com.pfe.ffs.services.client.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientWishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/addwishlist")
    public ResponseEntity<WishlistDto> addProduit(@RequestBody WishlistDto wishlistDto){
        WishlistDto wishlistDto1 = wishlistService.addProduit(wishlistDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistDto1);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlist(@PathVariable Long userId){
        List<WishlistDto> wishlistDtos = wishlistService.getAllProduitFav(userId);
        return ResponseEntity.ok(wishlistDtos);
    }

    @DeleteMapping("/wishlist/{Id}")
    public ResponseEntity<Void> delete(@PathVariable Long Id){
        boolean delete = wishlistService.deleteProd(Id);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
