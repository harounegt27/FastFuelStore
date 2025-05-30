package com.pfe.ffs.services.admin.commande;

import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ProduitCommanderDto;

import java.util.List;

public interface AdminCommandeService {
    public List<CommandeDto> getAllCommande();
    CommandeDto changeCommandeStatue(Long commandeId, String statue);
    Boolean deleteCommande(Long idCommande);
    CommandeDto getCommandeById(Long commandeId);

    ProduitCommanderDto produitCommanderDto(Long commandeId);
}
