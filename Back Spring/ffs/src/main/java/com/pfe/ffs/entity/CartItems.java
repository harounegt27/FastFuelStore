package com.pfe.ffs.entity;

import com.pfe.ffs.dto.CartItemsDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@Table(name = "cartitems")
@Entity
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantité",precision = 32, scale = 4)
    private BigDecimal qte;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "prix_id" , nullable = false)
    private PrixUnitaire prixUnitaire;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "voix_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Voix voix;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "produit_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;


    public CartItemsDto getDto(){
        CartItemsDto dto = new CartItemsDto();
        dto.setId(id);
        dto.setProduitId(produit.getId());
        dto.setVoixId(voix.getId());
        dto.setPrixUnitaireId(prixUnitaire.getId());
        dto.setUserId(user.getId());
        dto.setCommandeId(commande.getId());
        dto.setQte(qte);
        dto.setNomProduit(produit.getName());
        dto.setNomVoix(voix.getName());
        dto.setReturnedImg(produit.getImg());
        dto.setPrixUnitaire(prixUnitaire.getPrice());
        dto.setFrais1(prixUnitaire.getFrais1());
        dto.setFrais2(prixUnitaire.getFrais2());
        return dto;
    }

}
