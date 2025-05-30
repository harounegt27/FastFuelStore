package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProduitRepo extends JpaRepository<Produit,Long> {
    List<Produit> findAllByNameContaining(String title);
}
