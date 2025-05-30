package com.pfe.ffs.entity;

import com.pfe.ffs.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rating;

    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "user_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "produit_id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Produit produit;

    public ReviewDto getDto(){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(id);
        reviewDto.setDescription(description);
        reviewDto.setRating(rating);
        reviewDto.setUserId(user.getId());
        reviewDto.setProduitId(produit.getId());
        reviewDto.setUserName(user.getName());

        return reviewDto;

    }


}
