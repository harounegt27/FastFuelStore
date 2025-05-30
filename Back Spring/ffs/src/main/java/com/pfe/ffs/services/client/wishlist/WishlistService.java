package com.pfe.ffs.services.client.wishlist;

import com.pfe.ffs.dto.WishlistDto;

import java.util.List;

public interface WishlistService {
    public WishlistDto addProduit(WishlistDto wishlistDto);
    public List<WishlistDto> getAllProduitFav(Long userId);
    public Boolean deleteProd(Long id);
}
