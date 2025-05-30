package com.pfe.ffs.services.client.prixunitaire;

import com.pfe.ffs.dto.PrixUnitaireDto;

import java.util.List;

public interface ClientPrixUnitaireService {
    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(Long produitId);
}
