package com.pfe.ffs.services.admin.prixunitaire;

import com.pfe.ffs.dto.PrixUnitaireDto;

import java.io.IOException;
import java.util.List;

public interface PrixUnitaireService {
    public PrixUnitaireDto addPrix(PrixUnitaireDto prixUnitaireDto)throws IOException;
    public List<PrixUnitaireDto> getAllPrix();

    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(Long produitId);
    public Boolean deletePrixUnitaire(Long idPrix);
    public PrixUnitaireDto updatePrixUnitaire(Long prixId,PrixUnitaireDto prixUnitaireDto);
    public PrixUnitaireDto getPrixById(Long id);
}
