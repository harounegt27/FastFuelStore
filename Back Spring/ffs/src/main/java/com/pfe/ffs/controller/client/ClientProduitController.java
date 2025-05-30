package com.pfe.ffs.controller.client;


import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.services.client.produit.ClientProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientProduitController {
    private final ClientProduitService clientProduitService;

    @GetMapping("/recherche/{name}")
    public ResponseEntity<List<ProduitDto>> allProduitsByName(@PathVariable String name){
        List<ProduitDto> produitDtos = clientProduitService.allProduitsByName(name);
        return ResponseEntity.of(Optional.ofNullable(produitDtos));
    }

    @GetMapping("/produits")
    public ResponseEntity<List<ProduitDto>> allProduits(){
        List<ProduitDto> produitDtos = clientProduitService.allProduits();
        return ResponseEntity.of(Optional.ofNullable(produitDtos));
    }
}
