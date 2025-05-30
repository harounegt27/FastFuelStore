package com.pfe.ffs.services.client.wishlist;

import com.pfe.ffs.dto.WishlistDto;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.entity.Wishlist;
import com.pfe.ffs.repository.ProduitRepo;
import com.pfe.ffs.repository.UserRepo;
import com.pfe.ffs.repository.WishlistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{
    private final WishlistRepo wishlistRepo;
    private final ProduitRepo produitRepo;
    private final UserRepo userRepo;

    public WishlistDto addProduit(WishlistDto wishlistDto) {
        Long produitId = wishlistDto.getProduitId();
        Long userId = wishlistDto.getUserId();

        if (produitId == null) {
            // Gérer le cas où les identifiants sont null
            throw new IllegalArgumentException("Les identifiants ne peuvent pas être null1");
        }
        if (userId == null) {
            // Gérer le cas où les identifiants sont null
            throw new IllegalArgumentException("Les identifiants ne peuvent pas être null");
        }


        Optional<Produit> produit = produitRepo.findById(produitId);
        Optional<User> user = userRepo.findById(userId);

        if (produit.isPresent() && user.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setProduit(produit.get());
            wishlist.setUser(user.get());
            return wishlistRepo.save(wishlist).getDto();
        } else {
            throw new IllegalArgumentException("Produit ou utilisateur non trouvé pour les identifiants: " + produitId + ", " + userId);
        }
    }

    public List<WishlistDto> getAllProduitFav(Long userId){
        List<Wishlist> wishlist = wishlistRepo.findAllByUserId(userId);
        return wishlist.stream().map(Wishlist::getDto).collect(Collectors.toList());
    }


    public Boolean deleteProd(Long id){
        Optional<Wishlist> wishlist = wishlistRepo.findById(id);
        if (wishlist.isPresent()){
            wishlistRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
