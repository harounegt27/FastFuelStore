package com.pfe.ffs.controller.client;


import com.pfe.ffs.dto.AjouterProduitAuPanierDto;
import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ModifierQuantiteDuPanierDto;
import com.pfe.ffs.dto.PasserCommandeDto;
import com.pfe.ffs.services.client.cart.ClientCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientCartController {
    private final ClientCartService clientCartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProduitToCart(@RequestBody AjouterProduitAuPanierDto produitAuPanierDto){
        return clientCartService.AddProduitToCart(produitAuPanierDto);
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
        CommandeDto commandeDto = clientCartService.getCommandeByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(commandeDto);
    }

    @PostMapping("/cart/QTEUpdate")
    public ResponseEntity<CommandeDto> updateQuantity(@RequestBody ModifierQuantiteDuPanierDto updateDto) {
        CommandeDto updatedCommande = clientCartService.updateQuantity(updateDto);
        if (updatedCommande != null) {
            return new ResponseEntity<>(updatedCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/passerCommande")
    public ResponseEntity<CommandeDto> passerCommande(@RequestBody PasserCommandeDto passerCommandeDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientCartService.passerCommande(passerCommandeDto));
    }

    @GetMapping("/commandes/{userId}")
    public ResponseEntity<List<CommandeDto>> getAllCommandesClient(@PathVariable Long userId){
        List<CommandeDto> commandeDtos = clientCartService.getAllCommandesClient(userId);
        return ResponseEntity.ok(commandeDtos);
    }

    @DeleteMapping("/cart/{userId}/item/{produitId}")
    public ResponseEntity<?> deleteItemFromCart(@PathVariable Long userId, @PathVariable Long produitId) {
        boolean isDeleted = clientCartService.deleteElemFromCart(userId, produitId);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<CommandeDto> getCommandeById(@PathVariable Long commandeId){
        CommandeDto commandeDto = clientCartService.getCommandeById(commandeId);
        return ResponseEntity.ok(commandeDto);
    }

}