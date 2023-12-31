package com.templateproject.api.repository;

import com.templateproject.api.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Ingredient findByIngredientName(String ingredientName);
}
