package com.pfe.ffs.repository;

import com.pfe.ffs.entity.PrixUnitaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrixUnitaireRepo extends JpaRepository<PrixUnitaire,Long> {
    List<PrixUnitaire> findByProduitId(Long produitId);
    PrixUnitaire findByProduitIdAndVoixId(Long produitId, Long voixId);
}
