package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.CommandeDto;
import com.pfe.ffs.dto.ProduitCommanderDto;
import com.pfe.ffs.services.admin.commande.AdminCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCommandeController {
    private final AdminCommandeService commandeService;

    @GetMapping("/commandes")
    public ResponseEntity<List<CommandeDto>> getAllCommandesClient() {
        List<CommandeDto> commandeDtos = commandeService.getAllCommande();
        return ResponseEntity.ok(commandeDtos);
    }

    @GetMapping("/commande/{commandeId}/{statue}")
    public ResponseEntity<?> changeCommandeStatue(@PathVariable Long commandeId, @PathVariable String statue) {
        CommandeDto commandeDto = commandeService.changeCommandeStatue(commandeId,statue);
        if(commandeDto == null){
            return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(commandeDto);
    }

    @DeleteMapping("/supp-commande/{commandeId}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long commandeId){
        Boolean delete = commandeService.deleteCommande(commandeId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/commande/{commandeId}")
    public ResponseEntity<CommandeDto> getCommandeById(@PathVariable Long commandeId){
        CommandeDto commandeDto = commandeService.getCommandeById(commandeId);
        return ResponseEntity.ok(commandeDto);
    }


    @GetMapping("/produits-commander/{commandeId}")
    public ResponseEntity<ProduitCommanderDto> getProduitsByCommandeId(@PathVariable Long commandeId){
        return ResponseEntity.ok(commandeService.produitCommanderDto(commandeId));
    }

}
