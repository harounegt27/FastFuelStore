package com.pfe.ffs.services.client.review;

import com.pfe.ffs.dto.ProduitCommanderDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.ReviewDto;
import com.pfe.ffs.entity.*;
import com.pfe.ffs.repository.CommandeRepo;
import com.pfe.ffs.repository.ProduitRepo;
import com.pfe.ffs.repository.ReviewRepo;
import com.pfe.ffs.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final CommandeRepo commandeRepo;
    private final ProduitRepo produitRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;

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


    public ReviewDto giveReview(ReviewDto reviewDto) {
        Optional<Produit> optionalProduit = produitRepo.findById(reviewDto.getProduitId());
        Optional<User> optionalUser = userRepo.findById(reviewDto.getUserId());

        if (optionalProduit.isPresent() && optionalUser.isPresent()){
            Review review = new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduit(optionalProduit.get());



            return reviewRepo.save(review).getDto();
        }
        return null;
    }
}
