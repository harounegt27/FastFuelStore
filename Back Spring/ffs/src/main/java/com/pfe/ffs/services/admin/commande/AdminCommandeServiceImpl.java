package com.pfe.ffs.services.admin.commande;

import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ProduitCommanderDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.CartItems;
import com.pfe.ffs.entity.Commande;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.entity.Stock;
import com.pfe.ffs.enums.CommandeStatue;
import com.pfe.ffs.repository.CommandeRepo;
import com.pfe.ffs.repository.StockRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCommandeServiceImpl implements AdminCommandeService {
    private final CommandeRepo commandeRepo;
    private final StockRepo stockRepo;

    @Override
    public List<CommandeDto> getAllCommande() {
        List<Commande> commandeList = commandeRepo.findAllByCommandeStatueIn(
                List.of(CommandeStatue.En_traitement, CommandeStatue.En_livraison, CommandeStatue.Livrée)
        );
        return commandeList.stream().map(Commande::getDto).collect(Collectors.toList());
    }

    public CommandeDto changeCommandeStatue(Long commandeId, String statue) {
        Optional<Commande> optionalCommande = commandeRepo.findById(commandeId);

        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();

            if (Objects.equals(statue, "En livraison")) {
                List<CartItems> cartItems = commande.getCartItems();
                for (CartItems cartItem : cartItems) {
                    BigDecimal quantityOrderedTm = cartItem.getQte(); // Quantité commandée en TM
                    Produit produit = cartItem.getProduit(); // Produit associé au CartItem

                    // Récupérer le stock du produit
                    Stock stock = stockRepo.findByProduitId(produit.getId());
                    BigDecimal stockEnM3 = stock.getEn_m3();
                    BigDecimal stockEnTm = stock.getEn_tm();

                    // Vérifier si la quantité commandée en TM est supérieure au stock disponible
                    if (quantityOrderedTm.compareTo(stockEnTm) > 0) {
                        throw new IllegalArgumentException("La quantité commandée (" + quantityOrderedTm + " TM) dépasse le stock disponible (" + stockEnTm + " TM) pour le produit " + produit.getName());
                    }

                    // Récupérer la densité du produit (conversion de TM à m3)
                    BigDecimal densite = produit.getDensite();

                    // Calculer la quantité en m3
                    BigDecimal quantityOrderedM3 = quantityOrderedTm.multiply(densite);

                    // Mettre à jour le stock en fonction de la quantité commandée
                    BigDecimal nouvelleQuantiteEnM3 = stockEnM3.subtract(quantityOrderedM3);
                    BigDecimal nouvelleQuantiteEnTm = stockEnTm.subtract(quantityOrderedTm);

                    // Mettre à jour le stock du produit
                    stock.setEn_m3(nouvelleQuantiteEnM3);
                    stock.setEn_tm(nouvelleQuantiteEnTm);

                    // Sauvegarder les modifications du stock dans la base de données
                    stockRepo.save(stock);
                }
                commande.setCommandeStatue(CommandeStatue.En_livraison);
            } else if (Objects.equals(statue, "Livrée")) {
                commande.setDate_liv(new Date());
                commande.setCode_fact(UUID.randomUUID());
                commande.setCommandeStatue(CommandeStatue.Livrée);
            }
            return commandeRepo.save(commande).getDto();
        }
        return null;
    }

    public Boolean deleteCommande(Long idCommande){
        Optional<Commande> optionalCommande = commandeRepo.findById(idCommande);
        if (optionalCommande.isPresent()){
            commandeRepo.deleteById(idCommande);
            return true;
        }
        return false;
    }

    public CommandeDto getCommandeById(Long commandeId){
        Optional<Commande> commande = commandeRepo.findById(commandeId);
        if (commande.isPresent()){
            return commande.get().getDto();
        }
        return null;
    }

    public ProduitCommanderDto produitCommanderDto(Long commandeId){
        Optional<Commande> optionalCommande = commandeRepo.findById(commandeId);
        ProduitCommanderDto produitCommanderDto = new ProduitCommanderDto();

        if (optionalCommande.isPresent()){
            produitCommanderDto.setMontant_totale(optionalCommande.get().getMontant_totale());
            List<ProduitDto> produitDtoList = new ArrayList<>();
            for (CartItems cartItems : optionalCommande.get().getCartItems()){
                ProduitDto produitDto = new ProduitDto();

                produitDto.setId(cartItems.getProduit().getId());
                produitDto.setName(cartItems.getProduit().getName());
                produitDto.setQte(cartItems.getQte());
                produitDto.setPrixUnitaire(cartItems.getPrixUnitaire().getPrice());
                produitDto.setByteImg(cartItems.getProduit().getImg());

                produitDtoList.add(produitDto);
            }
            produitCommanderDto.setProduitDtosList(produitDtoList);
        }
        return produitCommanderDto;
    }
}
