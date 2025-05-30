package com.pfe.ffs.dto;

import lombok.Data;

@Data
public class AjouterProduitAuPanierDto {
    private Long userId;
    private Long produitId;
    private Long voixId;
}
