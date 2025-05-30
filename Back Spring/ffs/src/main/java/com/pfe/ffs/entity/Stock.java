package com.pfe.ffs.entity;


import com.pfe.ffs.dto.StockDto;
import com.pfe.ffs.enums.EtatStock;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "en_tm",precision = 32,scale = 4)
    private BigDecimal en_tm;

    @Column(name = "en_m3" ,precision = 32,scale = 4)
    private BigDecimal en_m3;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_m3")
    private EtatStock etatM3;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_tm")
    private EtatStock etatTm;

    @Column(name = "indiqateur_tm",precision = 32,scale = 4)
    private BigDecimal indiq_en_tm;

    @Column(name = "indicateur_m3" ,precision = 32,scale = 4)
    private BigDecimal indiq_en_m3;


    @OneToOne
    @JoinColumn(name = "produit_id", referencedColumnName = "id")
    private Produit produit;


    public StockDto getDto(){
        StockDto stockDto = new StockDto();
        stockDto.setId(id);
        stockDto.setEn_m3(en_m3);
        stockDto.setEn_tm(en_tm);
        stockDto.setProduitId(produit.getId());

        stockDto.setIndique_en_m3(indiq_en_m3);
        stockDto.setIndique_en_tm(indiq_en_tm);
        stockDto.setEtatM3(etatM3.toString());
        stockDto.setEtatTM(etatTm.toString());

        return stockDto;
    }
}
