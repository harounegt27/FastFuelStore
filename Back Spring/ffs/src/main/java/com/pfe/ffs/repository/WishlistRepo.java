package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Long> {
    List<Wishlist> findAllByUserId(Long userId);
}
