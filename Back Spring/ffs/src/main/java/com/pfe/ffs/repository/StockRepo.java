package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepo extends JpaRepository<Stock, Long> {
    Stock findByProduitId (Long produitId);
}
