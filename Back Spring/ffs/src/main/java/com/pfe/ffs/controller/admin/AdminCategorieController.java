package com.pfe.ffs.controller.admin;

import com.pfe.ffs.dto.CategorieDto;
import com.pfe.ffs.entity.Categorie;
import com.pfe.ffs.services.admin.categorie.CategorieSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminCategorieController {
    private final CategorieSevice categorieSevice;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/categorie")
    public ResponseEntity<Categorie> createCategorie(@RequestBody CategorieDto categorieDto){
        Categorie categorie = categorieSevice.createcategorie(categorieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorie);
    }

    @GetMapping("/tout-categorie")
    public ResponseEntity<List<Categorie>> getAllCategories() { // Utilisation de ResponseEntity<List<Categorie>>
        List<Categorie> categories = categorieSevice.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categorie/categorieId")
    public ResponseEntity<Categorie> getAllCategories(@PathVariable Long categorieId) { // Utilisation de ResponseEntity<List<Categorie>>
          Categorie categorie = categorieSevice.getCategorieById(categorieId);
        if (categorie != null) {
            return ResponseEntity.ok(categorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/supp-categorie/{categorieId}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long categorieId){
        boolean delete = categorieSevice.deleteCat(categorieId);
        if (delete){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/upd-categorie/{categorieId}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable Long categorieId, @RequestBody CategorieDto categorieDto) {
        Categorie updatedCategorie = categorieSevice.updateCategorie(categorieId, categorieDto);
        if (updatedCategorie != null) {
            return ResponseEntity.ok(updatedCategorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
