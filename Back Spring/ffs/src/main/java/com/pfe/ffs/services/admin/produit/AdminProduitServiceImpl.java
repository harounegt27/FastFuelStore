package com.pfe.ffs.services.admin.produit;

import com.pfe.ffs.dto.PrixUnitaireDto;
import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.Categorie;
import com.pfe.ffs.entity.PrixUnitaire;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.repository.CategorieRepo;
import com.pfe.ffs.repository.ProduitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProduitServiceImpl implements AdminProduitService {

    private final ProduitRepo produitRepo;
    private final CategorieRepo categorieRepo;

    public ProduitDto addPoduit(ProduitDto produitDto) throws IOException {
        Produit produit = new Produit();
        produit.setName(produitDto.getName());
        produit.setDescription(produitDto.getDescription());
        produit.setImg(produitDto.getImg().getBytes());
        produit.setDensite(produitDto.getDensite());

        Categorie categorie = categorieRepo.findById(produitDto.getCategorieId()).orElseThrow();
        produit.setCategorie(categorie);

        return produitRepo.save(produit).getDto();
    }

    public List<ProduitDto> allProduits(){
        List<Produit> produits = produitRepo.findAll();
        return produits.stream().map(Produit::getDto).collect(Collectors.toList());
    }

    public List<Produit> allProduitPU(){return produitRepo.findAll();}

    public List<ProduitDto> allProduitsByName(String name){
        List<Produit> produits = produitRepo.findAllByNameContaining(name);
        return produits.stream().map(Produit::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduit(Long id){
        Optional<Produit> produit = produitRepo.findById(id);
        if (produit.isPresent()){
            produitRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public ProduitDto getProduitById(Long id){
        Optional<Produit> produit = produitRepo.findById(id);
        if (produit.isPresent()){
            return produit.get().getDto();
        }else {
            return null;
        }
    }

    public ProduitDto updateProduit(Long produitId,ProduitDto produitDto) throws IOException {
        Optional<Produit> optionalProduit = produitRepo.findById(produitId);
        Optional<Categorie> optionalCategorie = categorieRepo.findById(produitDto.getCategorieId());
        if (optionalProduit.isPresent() && optionalCategorie.isPresent()){
            Produit produit = optionalProduit.get();
            produit.setName(produitDto.getName());
            produit.setDescription(produitDto.getDescription());
            produit.setCategorie(optionalCategorie.get());
            produit.setDensite(produitDto.getDensite());
            if (produitDto.getImg() != null){
                produit.setImg(produitDto.getImg().getBytes());
            }
            return produitRepo.save(produit).getDto();
        }else {
            return null;
        }
    }

}
