package com.pfe.ffs.entity;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.dto.WishlistDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public WishlistDto getDto() {
        WishlistDto wishlistDto = new WishlistDto();
        wishlistDto.setId(id);
        wishlistDto.setProduitId(produit.getId());
        wishlistDto.setUserId(user.getId());
        wishlistDto.setProduitName(produit.getName());
        wishlistDto.setNomCategorie(produit.getCategorie().getName());
        wishlistDto.setProduitDescri(produit.getDescription());
        return wishlistDto;
    }
}
