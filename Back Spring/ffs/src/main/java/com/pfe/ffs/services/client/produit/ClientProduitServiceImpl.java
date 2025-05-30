package com.pfe.ffs.services.client.produit;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.Produit;
import com.pfe.ffs.repository.ProduitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProduitServiceImpl implements ClientProduitService{
    private final ProduitRepo produitRepo;
    public List<ProduitDto> allProduits(){
        List<Produit> produits = produitRepo.findAll();
        return produits.stream().map(Produit::getDto).collect(Collectors.toList());
    }

    public List<ProduitDto> allProduitsByName(String name){
        List<Produit> produits = produitRepo.findAllByNameContaining(name);
        return produits.stream().map(Produit::getDto).collect(Collectors.toList());
    }
}
