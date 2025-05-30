package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategorieRepo extends JpaRepository<Categorie ,Long> {
}
