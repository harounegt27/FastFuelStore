package com.pfe.ffs.dto;

import com.pfe.ffs.enums.EtatStock;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockDto {
    private Long id;
    private BigDecimal en_tm;
    private BigDecimal en_m3;
    private BigDecimal indique_en_tm;
    private BigDecimal indique_en_m3;
    private Long produitId;
    private String etatTM;
    private String etatM3;
}
