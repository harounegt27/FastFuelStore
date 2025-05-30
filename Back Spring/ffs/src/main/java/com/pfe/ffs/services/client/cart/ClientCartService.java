package com.pfe.ffs.services.client.cart;

import com.pfe.ffs.dto.AjouterProduitAuPanierDto;
import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ModifierQuantiteDuPanierDto;
import com.pfe.ffs.dto.PasserCommandeDto;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ClientCartService {
    public ResponseEntity<?> AddProduitToCart(AjouterProduitAuPanierDto produitAuPanierDto);
    public CommandeDto getCommandeByUserId(Long userId);
    public CommandeDto updateQuantity(ModifierQuantiteDuPanierDto updateDto);
    public CommandeDto passerCommande(PasserCommandeDto passerCommandeDto);
    public List<CommandeDto> getAllCommandesClient(Long userId);
    public Boolean deleteElemFromCart(Long userId, Long produitId);
    CommandeDto getCommandeById(Long commandeId);
}
