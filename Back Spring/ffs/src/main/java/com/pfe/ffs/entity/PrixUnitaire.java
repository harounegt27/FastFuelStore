package com.pfe.ffs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.ffs.dto.PrixUnitaireDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "prixUnitaire")
@Data
public class PrixUnitaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price" ,precision = 32, scale = 3)
    private BigDecimal price;

    @Column(name = "costs1",precision = 32, scale = 3)
    private BigDecimal frais1;

    @Column(name = "costs2",precision = 32, scale = 3)
    private BigDecimal frais2;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produit_id")
    @JsonIgnore
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "voix_id")
    private Voix voix;

    @Column(name = "structure_prix")
    private Date structure_prix ;


    public PrixUnitaireDto getDto() {
        PrixUnitaireDto prixUnitaireDto = new PrixUnitaireDto();
        prixUnitaireDto.setId(id);
        prixUnitaireDto.setPrice(price);
        prixUnitaireDto.setFrais1(frais1);
        prixUnitaireDto.setFrais2(frais2);
        prixUnitaireDto.setStructure_prix(structure_prix);
        prixUnitaireDto.setProduitId(produit.getId());
        prixUnitaireDto.setVoixId(voix.getId());

        return prixUnitaireDto;
    }
}
