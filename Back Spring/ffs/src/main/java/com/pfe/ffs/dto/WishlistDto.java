package com.pfe.ffs.dto;

import lombok.Data;

@Data
public class WishlistDto {
    private Long id;
    private Long produitId;
    private Long userId;
    private String produitName;
    private String nomCategorie;
    private String produitDescri;
}