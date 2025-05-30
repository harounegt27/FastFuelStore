package com.pfe.ffs.services.client.prixunitaire;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.entity.PrixUnitaire;
import com.pfe.ffs.repository.PrixUnitaireRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientPrixUnitaireServiceImpl implements ClientPrixUnitaireService{
    private final PrixUnitaireRepo prixUnitaireRepo;

    @Override
    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(Long produitId) {
        List<PrixUnitaire> prixUnitaires = prixUnitaireRepo.findByProduitId(produitId);
        return prixUnitaires.stream()
                .map(PrixUnitaire::getDto)
                .collect(Collectors.toList());
    }
}
