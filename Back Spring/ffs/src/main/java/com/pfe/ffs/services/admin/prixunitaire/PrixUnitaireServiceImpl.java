package com.pfe.ffs.services.admin.prixunitaire;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.PrixUnitaire;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.entity.Voix;
import com.pfe.ffs.repository.PrixUnitaireRepo;
import com.pfe.ffs.repository.ProduitRepo;
import com.pfe.ffs.repository.VoixRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrixUnitaireServiceImpl implements PrixUnitaireService{
    private final PrixUnitaireRepo prixUnitaireRepo;
    private final VoixRepo voixRepo;
    private final ProduitRepo produitRepo;

    public PrixUnitaireDto addPrix(PrixUnitaireDto prixUnitaireDto) throws IOException {
        PrixUnitaire prixUnitaire = new PrixUnitaire();

        prixUnitaire.setPrice(prixUnitaireDto.getPrice());
        prixUnitaire.setFrais1(prixUnitaireDto.getFrais1());
        prixUnitaire.setFrais2(prixUnitaireDto.getFrais2());
        prixUnitaire.setStructure_prix(prixUnitaireDto.getStructure_prix());

        Voix voix = voixRepo.findById(prixUnitaireDto.getVoixId()).orElseThrow();
        Produit produit = produitRepo.findById(prixUnitaireDto.getProduitId()).orElseThrow();

        prixUnitaire.setProduit(produit);
        prixUnitaire.setVoix(voix);
        return prixUnitaireRepo.save(prixUnitaire).getDto();
    }

    public List<PrixUnitaireDto> getAllPrix(){
        List<PrixUnitaire> prixUnitaires = prixUnitaireRepo.findAll();
        return prixUnitaires.stream().map(PrixUnitaire::getDto).collect(Collectors.toList());
    }

    @Override
    public List<PrixUnitaireDto> findPrixUnitairesByProduitId(Long produitId) {
        List<PrixUnitaire> prixUnitaires = prixUnitaireRepo.findByProduitId(produitId);
        return prixUnitaires.stream()
                .map(PrixUnitaire::getDto)
                .collect(Collectors.toList());
    }

    public Boolean deletePrixUnitaire(Long idPrix){
        Optional<PrixUnitaire> prixUnitaire = prixUnitaireRepo.findById(idPrix);
        if (prixUnitaire.isPresent()){
            prixUnitaireRepo.deleteById(idPrix);
            return true;
        }
        return false;
    }

    public PrixUnitaireDto getPrixById(Long id){
        Optional<PrixUnitaire> prixUnitaire = prixUnitaireRepo.findById(id);
        if (prixUnitaire.isPresent()){
            return prixUnitaire.get().getDto();
        }else {
            return null;
        }
    }

    public PrixUnitaireDto updatePrixUnitaire(Long prixId,PrixUnitaireDto prixUnitaireDto){
        Optional<PrixUnitaire> optionalPrixUnitaire = prixUnitaireRepo.findById(prixId);
        Optional<Produit> optionalProduit = produitRepo.findById(prixUnitaireDto.getProduitId());
        Optional<Voix> optionalVoix = voixRepo.findById(prixUnitaireDto.getVoixId());

        if (optionalPrixUnitaire.isPresent() && optionalProduit.isPresent() && optionalVoix.isPresent()){
            PrixUnitaire prixUnitaire = optionalPrixUnitaire.get();
            prixUnitaire.setPrice(prixUnitaireDto.getPrice());
            prixUnitaire.setFrais1(prixUnitaireDto.getFrais1());
            prixUnitaire.setFrais2(prixUnitaireDto.getFrais2());
            prixUnitaire.setStructure_prix(prixUnitaireDto.getStructure_prix());
            prixUnitaire.setVoix(optionalVoix.get());
            prixUnitaire.setProduit(optionalProduit.get());

            return prixUnitaireRepo.save(prixUnitaire).getDto();
        }else {
            return null;
        }
    }
}
