package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.services.admin.produit.AdminProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProduitController {
    private final AdminProduitService adminProduitService;

    @PostMapping("/produit")
    public ResponseEntity<ProduitDto> addProduit(@ModelAttribute ProduitDto produitDto) throws IOException {
        ProduitDto produitDto1 = adminProduitService.addPoduit(produitDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produitDto1);
    }

    @GetMapping("/produits")
    public ResponseEntity<List<ProduitDto>> allProduits(){
        List<ProduitDto> produitDtos = adminProduitService.allProduits();
        return ResponseEntity.of(Optional.ofNullable(produitDtos));
    }

    @GetMapping("/produitsPU")
    public ResponseEntity<List<Produit>> allProduitsPU(){
        List<Produit> produit = adminProduitService.allProduitPU();
        return ResponseEntity.ok(produit);
    }

    @GetMapping("/recherche/{name}")
    public ResponseEntity<List<ProduitDto>> allProduitsByName(@PathVariable String name){
        List<ProduitDto> produitDtos = adminProduitService.allProduitsByName(name);
        return ResponseEntity.of(Optional.ofNullable(produitDtos));
    }

    @DeleteMapping("/produit/{produitId}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long produitId){
        boolean delete = adminProduitService.deleteProduit(produitId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/produit/{produitId}")
    public ResponseEntity<ProduitDto> getProduitById(@PathVariable Long produitId){
        ProduitDto produitDto = adminProduitService.getProduitById(produitId);
        if (produitDto != null){
            return ResponseEntity.ok(produitDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/produit/{produitId}")
    public ResponseEntity<ProduitDto> updateProduit(@PathVariable Long produitId,@ModelAttribute ProduitDto produitDto) throws IOException {
        ProduitDto updatedProduit = adminProduitService.updateProduit(produitId,produitDto);
        if (updatedProduit != null){
            return ResponseEntity.ok(updatedProduit);
        }else {
            return ResponseEntity.notFound().build();
        }
    }





}
