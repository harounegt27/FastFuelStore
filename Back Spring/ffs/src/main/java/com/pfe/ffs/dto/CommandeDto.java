package com.pfe.ffs.dto;

import com.pfe.ffs.entity.CartItems;
import com.pfe.ffs.entity.User;
import com.pfe.ffs.enums.CommandeStatue;
import com.pfe.ffs.enums.Paiment;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class CommandeDto {

    private Long id;

    private Long userId;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private BigDecimal montant;

    private UUID code;

    private UUID code_fact;

    private BigDecimal montant_totale;

    private CommandeStatue commandeStatue;

    private List<CartItemsDto> cartItems ;

    private String adresse;

    private Paiment paiment;

    private Date date_liv;
}
