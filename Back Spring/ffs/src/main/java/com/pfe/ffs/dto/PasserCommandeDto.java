package com.pfe.ffs.dto;

import com.pfe.ffs.enums.Paiment;
import lombok.Data;

@Data
public class PasserCommandeDto {
    private Long userId;
    private String adresse;
    private String paiment;
}
