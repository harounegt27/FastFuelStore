package com.pfe.ffs.repository;

import com.pfe.ffs.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    CartItems findByProduitIdAndCommandeIdAndUserId(Long produitId, Long commandeId, Long userId);

}
