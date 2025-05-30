package com.pfe.ffs.services.client.cart;


import com.pfe.ffs.dto.*;
import com.pfe.ffs.entity.*;
import com.pfe.ffs.enums.CommandeStatue;
import com.pfe.ffs.enums.Paiment;
import com.pfe.ffs.repository.*;
import com.pfe.ffs.utils.EnumUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientCartServiceImpl implements ClientCartService{
    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private CommandeRepo commandeRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProduitRepo produitRepo;

    @Autowired
    private VoixRepo voixRepo;

    @Autowired
    private PrixUnitaireRepo prixUnitaireRepo;

    public ResponseEntity<?> AddProduitToCart(AjouterProduitAuPanierDto produitAuPanierDto){
        Commande commandeActive = commandeRepo.findByUserIdAndCommandeStatue(produitAuPanierDto.getUserId(), CommandeStatue.En_attend);
        Optional<CartItems> optionalCartItems = Optional.ofNullable(cartItemsRepo.findByProduitIdAndCommandeIdAndUserId
                (produitAuPanierDto.getProduitId(), commandeActive.getId(), produitAuPanierDto.getUserId()));

        if (optionalCartItems.isPresent()){
            ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Produit> optionalProduit = produitRepo.findById(produitAuPanierDto.getProduitId());
            Optional<User> optionalUser = userRepo.findById(produitAuPanierDto.getUserId());
            Optional<Voix> optionalVoix = voixRepo.findById(produitAuPanierDto.getVoixId());
            if (optionalUser.isPresent() && optionalProduit.isPresent() && optionalVoix.isPresent()){
                CartItems cartItems = new CartItems();
                cartItems.setProduit(optionalProduit.get());
                cartItems.setUser(optionalUser.get());
                cartItems.setVoix(optionalVoix.get());
                cartItems.setQte(BigDecimal.valueOf(1));
                cartItems.setPrixUnitaire(prixUnitaireRepo.findByProduitIdAndVoixId(
                        optionalProduit.get().getId(),optionalVoix.get().getId()
                ));
                cartItems.setCommande(commandeActive);

                CartItems updatedCart = cartItemsRepo.save(cartItems);

                BigDecimal prix = cartItems.getPrixUnitaire().getPrice();
                BigDecimal fais1 = cartItems.getPrixUnitaire().getFrais1();
                BigDecimal fais2 = cartItems.getPrixUnitaire().getFrais2();

                // Calcul du montant pour cet élément du panier
                BigDecimal montantElement = prix.multiply(cartItems.getQte());
                // Ajout du montant à la commande active
                commandeActive.setMontant(commandeActive.getMontant().add(montantElement));

                // Calcul du montant total pour cet élément du panier
                BigDecimal montantTotalElement = prix.add(fais1).add(fais2).multiply(cartItems.getQte());
                // Ajout du montant total à la commande active
                commandeActive.setMontant_totale(commandeActive.getMontant_totale().add(montantTotalElement));


                if (commandeActive.getCartItems() == null) {
                    commandeActive.setCartItems(new ArrayList<>());
                }
                commandeActive.getCartItems().add(cartItems);

                commandeRepo.save(commandeActive);

                return ResponseEntity.status(HttpStatus.CREATED).body(cartItems);
            }else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User ou Produit introuvable !");
            }
        }
        return null;
    }
    public CommandeDto getCommandeByUserId(Long userId){
        Commande commandeActive = commandeRepo.findByUserIdAndCommandeStatue(userId, CommandeStatue.En_attend);

        List<CartItemsDto> itemsDtoList = commandeActive.getCartItems().stream().map(CartItems::getDto).collect(Collectors.toList());

        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setMontant(commandeActive.getMontant());
        commandeDto.setMontant_totale(commandeActive.getMontant_totale());
        commandeDto.setId(commandeActive.getId());
        commandeDto.setCommandeStatue(commandeActive.getCommandeStatue());
        commandeDto.setCartItems(itemsDtoList);

        return commandeDto;
    }

    public CommandeDto updateQuantity(ModifierQuantiteDuPanierDto updateDto) {
        Commande commandeActive = commandeRepo.findByUserIdAndCommandeStatue(updateDto.getUserId(), CommandeStatue.En_attend);
        Optional<Produit> optionalProduit = produitRepo.findById(updateDto.getProduitId());
        Optional<CartItems> optionalCartItems = Optional.ofNullable(cartItemsRepo.findByProduitIdAndCommandeIdAndUserId(
                updateDto.getProduitId(), commandeActive.getId(), updateDto.getUserId()));

        if (optionalProduit.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get ();
            BigDecimal newQte = updateDto.getNewQte(); // Nouvelle quantité
            BigDecimal oldQte = cartItems.getQte();
            BigDecimal prix = cartItems.getPrixUnitaire().getPrice();
            BigDecimal fais1 = cartItems.getPrixUnitaire().getFrais1();
            BigDecimal fais2 = cartItems.getPrixUnitaire().getFrais2();

            //Supprission des montants ancien
            commandeActive.setMontant(commandeActive.getMontant().subtract(prix.multiply(oldQte)));
            commandeActive.setMontant_totale(commandeActive.getMontant_totale().subtract(prix.add(fais1).add(fais2).multiply(oldQte)));

            // Mettre à jour la quantité
            cartItems.setQte(newQte);



            // Mettre à jour le montant de la commande
            commandeActive.setMontant(commandeActive.getMontant().add(prix.multiply(newQte)));

            // Mettre à jour le montant total de la commande
            BigDecimal totalFrais = fais1.add(fais2);
            BigDecimal montantTotal = prix.add(totalFrais).multiply(newQte);
            commandeActive.setMontant_totale(commandeActive.getMontant_totale().add(montantTotal));

            // Enregistrer les modifications
            cartItemsRepo.save(cartItems);
            commandeRepo.save(commandeActive);

            // Retourner la commande mise à jour ou null si nécessaire
            return commandeActive.getDto();
        }
        return null;
    }

    public CommandeDto passerCommande(PasserCommandeDto passerCommandeDto){
        if (passerCommandeDto.getUserId() != null) { // Vérification de la valeur de userId
            Commande activeCommande = commandeRepo.findByUserIdAndCommandeStatue(passerCommandeDto.getUserId(),CommandeStatue.En_attend);
            Optional<User> optionalUser = userRepo.findById(passerCommandeDto.getUserId());
            if (optionalUser.isPresent()){
                activeCommande.setAdresse(passerCommandeDto.getAdresse());
                activeCommande.setDate(new Date());
                activeCommande.setCode(UUID.randomUUID());
                activeCommande.setCode_fact(UUID.randomUUID());

                if ("CHEQUE_BANCAIRE".equals(passerCommandeDto.getPaiment())) {
                    activeCommande.setPaiment(Paiment.CHEQUE_BANCAIRE);
                } else if ("VIREMENT".equals(passerCommandeDto.getPaiment())) {
                    activeCommande.setPaiment(Paiment.VIREMENT);
                }
                activeCommande.setCommandeStatue(CommandeStatue.En_traitement);

                commandeRepo.save(activeCommande);

                Commande commande = new Commande();
                commande.setMontant(BigDecimal.valueOf(0.0));
                commande.setMontant_totale(BigDecimal.valueOf(0.0));
                commande.setCommandeStatue(CommandeStatue.En_attend);
                commande.setUser(optionalUser.get());
                commandeRepo.save(commande);

                return activeCommande.getDto();
            }
        }
        return null;
    }

    public List<CommandeDto> getAllCommandesClient(Long userId){
        List<Commande> commandes = commandeRepo.findAllByUserIdAndCommandeStatueIn(userId,List.of
                (CommandeStatue.En_traitement,CommandeStatue.En_livraison,CommandeStatue.Livrée)
        );
        return  commandes.stream().map(Commande::getDto).collect(Collectors.toList());
    }

    private Paiment convertirPaiment(String paimentString) {
        if (paimentString == null) {
            return null;
        }
        try {
            return Paiment.valueOf(paimentString.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Boolean deleteElemFromCart(Long userId, Long produitId) {
        Commande commandeActive = commandeRepo.findByUserIdAndCommandeStatue(userId, CommandeStatue.En_attend);
        if (commandeActive == null) {
            return false; // Aucune commande active pour cet utilisateur
        }

        Optional<CartItems> optionalCartItems = Optional.ofNullable(cartItemsRepo.findByProduitIdAndCommandeIdAndUserId(produitId, commandeActive.getId(), userId));
        if (!optionalCartItems.isPresent()) {
            return false; // L'élément n'est pas trouvé dans le panier
        }

        CartItems cartItem = optionalCartItems.get();
        BigDecimal prix = cartItem.getPrixUnitaire().getPrice();
        BigDecimal fais1 = cartItem.getPrixUnitaire().getFrais1();
        BigDecimal fais2 = cartItem.getPrixUnitaire().getFrais2();

        // Suppression des montants associés à cet élément du panier
        commandeActive.setMontant(commandeActive.getMontant().subtract(prix.multiply(cartItem.getQte())));
        commandeActive.setMontant_totale(commandeActive.getMontant_totale().subtract(prix.add(fais1).add(fais2).multiply(cartItem.getQte())));

        // Suppression de l'élément du panier
        cartItemsRepo.delete(cartItem);

        // Mise à jour de la commande
        commandeRepo.save(commandeActive);

        return true; // La suppression a été effectuée avec succès
    }


    public CommandeDto getCommandeById(Long commandeId){
        Optional<Commande> commande = commandeRepo.findById(commandeId);
        if (commande.isPresent()){
            return commande.get().getDto();
        }
        return null;
    }
}
