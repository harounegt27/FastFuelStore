package com.pfe.ffs.controller.client;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.services.client.prixunitaire.ClientPrixUnitaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientPrixUnitaireController {
    private final ClientPrixUnitaireService service;

    @GetMapping("/prodprix/{produitId}")
    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(@PathVariable Long produitId) {
        List<PrixUnitaireDto> unitaireDtos = service.findPrixUnitairesByProduitId(produitId);
        return unitaireDtos;
    }
}
