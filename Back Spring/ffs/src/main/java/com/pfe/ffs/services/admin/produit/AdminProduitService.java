package com.pfe.ffs.services.admin.produit;

import com.pfe.ffs.dto.ProduitDto;
import com.pfe.ffs.entity.Produit;

import java.io.IOException;
import java.util.List;

public interface AdminProduitService {
    public ProduitDto addPoduit(ProduitDto produitDto) throws IOException;
    public List<ProduitDto> allProduits();
    public List<Produit> allProduitPU();
    public List<ProduitDto> allProduitsByName(String name);
    public boolean deleteProduit(Long id);
    ProduitDto getProduitById(Long id);
    public ProduitDto updateProduit(Long produitId,ProduitDto produitDto) throws IOException;
}
