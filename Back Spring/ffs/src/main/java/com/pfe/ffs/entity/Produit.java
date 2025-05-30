package com.pfe.ffs.entity;


import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.ProduitDto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "produit")
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "img",columnDefinition = "bytea")
    private byte[] img;

    @Column(name = "densite")
    private BigDecimal densite;

    @ManyToOne
    @JoinColumn(name = "categorie_id", foreignKey = @ForeignKey(name = "fk_produit_categorie", foreignKeyDefinition = "FOREIGN KEY (categorie_id) REFERENCES categorie(id) ON DELETE CASCADE"))
    private Categorie categorie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "prices")
    private List<PrixUnitaire> prices;

    @OneToOne(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Stock stock;




    public ProduitDto getDto() {
        ProduitDto produitDto = new ProduitDto();
        produitDto.setId(id);
        produitDto.setName(name);
        produitDto.setDescription(description);
        produitDto.setDensite(densite);
        produitDto.setByteImg(img);
        produitDto.setCategorieId(categorie.getId());
        produitDto.setCategoriName(categorie.getName());
        return produitDto;
    }

}
