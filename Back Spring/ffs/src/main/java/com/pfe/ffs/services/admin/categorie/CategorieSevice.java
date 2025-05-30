package com.pfe.ffs.services.admin.categorie;

import com.pfe.ffs.dto.CategorieDto;
import com.pfe.ffs.entity.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategorieSevice {
    public Categorie createcategorie(CategorieDto categorieDto);
    public List<Categorie> getAllCategories();
    public Boolean deleteCat(Long id);
    public Categorie updateCategorie(Long id, CategorieDto categorieDto);
    public  Categorie getCategorieById(Long id);
}
