package com.pfe.ffs.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProduitDto {
    private Long id;
    private String name;
    private String description;
    private Long categorieId;
    private String categoriName;
    private BigDecimal densite;
    private byte[] byteImg;
    private MultipartFile img;
    private BigDecimal qte;
    private BigDecimal prixUnitaire;
}
