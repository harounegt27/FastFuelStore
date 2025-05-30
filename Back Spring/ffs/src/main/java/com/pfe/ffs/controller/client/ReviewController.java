package com.pfe.ffs.controller.client;

import com.pfe.ffs.dto.ProduitCommanderDto;
import com.pfe.ffs.dto.ReviewDto;
import com.pfe.ffs.services.client.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/produits-commander/{commandeId}")
    public ResponseEntity<ProduitCommanderDto> getProduitsByCommandeId(@PathVariable Long commandeId){
        return ResponseEntity.ok(reviewService.produitCommanderDto(commandeId));
    }


    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto){
        ReviewDto dto = reviewService.giveReview(reviewDto);
        if (dto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NULL");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto);
    }
}
