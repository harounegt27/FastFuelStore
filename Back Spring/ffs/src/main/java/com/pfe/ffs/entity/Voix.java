package com.pfe.ffs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "voix")
@Data
public class Voix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

}
