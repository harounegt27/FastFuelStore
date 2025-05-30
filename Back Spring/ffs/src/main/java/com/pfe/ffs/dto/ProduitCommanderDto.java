package com.pfe.ffs.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProduitCommanderDto {

    private List<ProduitDto> produitDtosList;

    private BigDecimal montant_totale;
}
