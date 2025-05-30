package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.services.admin.prixunitaire.PrixUnitaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminPrixUnitaireController {
    private final PrixUnitaireService prixUnitaireService;

    @PostMapping("/prix-unit")
    public ResponseEntity<PrixUnitaireDto> addPrix(PrixUnitaireDto prixUnitaireDto) throws IOException {
        PrixUnitaireDto prixUnitaireDto1 = prixUnitaireService.addPrix(prixUnitaireDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prixUnitaireDto1);
    }

    @GetMapping("/tout-prix")
    public ResponseEntity<List<PrixUnitaireDto>>  getAllPrixs(){
        List<PrixUnitaireDto> prixUnitaireDtos = prixUnitaireService.getAllPrix();
        return ResponseEntity.ok(prixUnitaireDtos);
    }

    @GetMapping("/prodprix/{produitId}")
    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(@PathVariable Long produitId) {
        List<PrixUnitaireDto> unitaireDtos = prixUnitaireService.findPrixUnitairesByProduitId(produitId);
        return unitaireDtos;
    }

    @DeleteMapping("/prix-unit/{prixId}")
    public ResponseEntity<Void> deletePrixUnitaire(@PathVariable Long prixId){
        Boolean delete = prixUnitaireService.deletePrixUnitaire(prixId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/prix-unit/{prixId}")
    public ResponseEntity<PrixUnitaireDto> getPrixById(@PathVariable Long prixId){
        PrixUnitaireDto prixUnitaireDto = prixUnitaireService.getPrixById(prixId);
        if (prixUnitaireDto != null){
            return ResponseEntity.ok(prixUnitaireDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/prix-unit/{prixId}")
    public ResponseEntity<PrixUnitaireDto> updatePrixUnitaire(@PathVariable Long prixId,PrixUnitaireDto prixUnitaireDto){
        PrixUnitaireDto updatedPrixUnitaire = prixUnitaireService.updatePrixUnitaire(prixId,prixUnitaireDto);
        if (updatedPrixUnitaire != null){
            return ResponseEntity.ok(updatedPrixUnitaire);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
