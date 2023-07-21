package com.templateproject.api.repository;

import com.templateproject.api.entity.IngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
    List<IngredientRecipe> findByIngredientIngredientNameContainingIgnoreCase(String ingredientName);

}
