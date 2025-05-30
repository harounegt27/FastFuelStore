package com.pfe.ffs.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ModifierQuantiteDuPanierDto {
    private Long userId;
    private Long produitId;
    private BigDecimal newQte;
}
