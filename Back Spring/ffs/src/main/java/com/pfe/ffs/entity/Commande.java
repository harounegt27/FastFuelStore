package com.pfe.ffs.entity;

import com.pfe.ffs.dto.CartItemsDto;
import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.enums.CommandeStatue;
import com.pfe.ffs.enums.Paiment;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "date_livraison")
    private Date date_liv;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "paiment")
    private Paiment paiment;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "montant",precision = 32, scale = 3)
    private BigDecimal montant;

    @Column(name = "code")
    private UUID code;

    @Column(name = "code_fact")
    private UUID code_fact;

    @Column(name = "montant_totale",precision = 32, scale = 3)
    private BigDecimal montant_totale;

    @Column(name = "statue")
    private CommandeStatue commandeStatue;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "commande")
    @Column(name = "carte_items")
    private List<CartItems> cartItems ;


    public CommandeDto getDto() {
        CommandeDto dto = new CommandeDto();
        dto.setId(id);
        dto.setDate(date);
        dto.setDate_liv(date_liv);
        dto.setAdresse(adresse);
        dto.setPaiment(paiment);
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setMontant(montant);
        dto.setCode(code);
        dto.setMontant_totale(montant_totale);
        dto.setCommandeStatue(commandeStatue);
        dto.setCode_fact(code_fact);

        List<CartItemsDto> cartItemsDtos = new ArrayList<>();
        for (CartItems cartItem : cartItems) {
            cartItemsDtos.add(cartItem.getDto());
        }
        dto.setCartItems(cartItemsDtos);

        return dto;
    }
}
