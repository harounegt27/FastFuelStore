package com.pfe.ffs.repository;

import com.pfe.ffs.entity.Commande;
import com.pfe.ffs.enums.CommandeStatue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepo extends JpaRepository<Commande,Long> {
    Commande findByUserIdAndCommandeStatue(Long userId , CommandeStatue commandeStatue);
    List<Commande> findAllByCommandeStatueIn(List<CommandeStatue> commandeStatues);

    List<Commande> findAllByUserIdAndCommandeStatueIn(Long userId,List<CommandeStatue> commandeStatues);
    List<Commande> findAllByUserId(Long userId);

}
