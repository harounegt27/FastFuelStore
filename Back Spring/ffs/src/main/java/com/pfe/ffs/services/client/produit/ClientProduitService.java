package com.pfe.ffs.services.client.produit;

import com.pfe.ffs.dto.ProduitDto;

import java.util.List;

public interface ClientProduitService {
    public List<ProduitDto> allProduits();
    public List<ProduitDto> allProduitsByName(String name);
}
