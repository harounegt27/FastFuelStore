package com.pfe.ffs.services.admin.categorie;


import com.pfe.ffs.dto.CategorieDto;
import com.pfe.ffs.entity.Categorie;
import com.pfe.ffs.repository.CategorieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieSevice{
    private final CategorieRepo categorieRepo ;

    @Override
    public Categorie createcategorie(CategorieDto categorieDto){
        Categorie categorie = new Categorie();
        categorie.setName(categorieDto.getName());
        categorie.setDescription(categorieDto.getDescription());

        return categorieRepo.save(categorie);
    }
    @Override
    public List<Categorie> getAllCategories(){
        return categorieRepo.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepo.findById(id).orElse(null);
    }

    public Boolean deleteCat(Long id){
        Optional<Categorie> categorie = categorieRepo.findById(id);
        if (categorie.isPresent()){
            categorieRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Categorie updateCategorie(Long id, CategorieDto categorieDto) {
        Optional<Categorie> optionalCategorie = categorieRepo.findById(id);
        if (optionalCategorie.isPresent()) {
            Categorie categorie = optionalCategorie.get();
            categorie.setName(categorieDto.getName());
            categorie.setDescription(categorieDto.getDescription());
            return categorieRepo.save(categorie); // Retourne directement la catégorie mise à jour
        } else {
            throw new RuntimeException("Catégorie non trouvée avec l'ID : " + id);
        }
    }
}
