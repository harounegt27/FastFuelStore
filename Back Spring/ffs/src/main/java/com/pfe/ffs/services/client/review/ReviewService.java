package com.pfe.ffs.services.client.review;

import com.pfe.ffs.dto.ProduitCommanderDto;
import com.pfe.ffs.dto.ReviewDto;

public interface ReviewService {
     ProduitCommanderDto produitCommanderDto(Long commandeId);
     ReviewDto giveReview(ReviewDto reviewDto);
}
