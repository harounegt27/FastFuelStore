package com.pfe.ffs.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PrixUnitaireDto {
    private Long id;
    private BigDecimal price;
    private BigDecimal frais1;
    private BigDecimal frais2;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date structure_prix;
    private Long voixId;
    private Long produitId;
}
