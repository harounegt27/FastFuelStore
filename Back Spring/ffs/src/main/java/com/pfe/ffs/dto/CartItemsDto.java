package com.pfe.ffs.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemsDto {
    private Long id;
    private Long produitId;
    private Long voixId;
    private Long prixUnitaireId;
    private Long userId;
    private Long commandeId;
    private BigDecimal qte;
    private String nomProduit;
    private byte[] returnedImg;
    private String nomVoix;
    private BigDecimal prixUnitaire;
    private BigDecimal frais1;
    private BigDecimal frais2;
}
