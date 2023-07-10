package com.templateproject.api.repository;

import com.templateproject.api.entity.IngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ingredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
}