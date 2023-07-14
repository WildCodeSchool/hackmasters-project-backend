package com.templateproject.api.repository;

import com.templateproject.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe findByRecipeNameIgnoreCase(String recipeName);
    List<Recipe> findByRecipeNameContainingIgnoreCase(String recipeName);
    List<Recipe> findByCountryCountryNameInIgnoreCase(List<String> countryNames);
    List<Recipe> findByCategoryCategoryNameInIgnoreCase(List<String> categoryNames);
    List<Recipe> findByDietsDietNameInIgnoreCase(List<String> dietNames);
    List<Recipe> findByAllergensAllergenNameInIgnoreCase(List<String> allergenNames);
    List<Recipe> findByCountryCountryNameInIgnoreCaseAndCategoryCategoryNameInIgnoreCase(List<String> countryNames, List<String> categoryNames);
    List<Recipe> findAllByIdNotIn(List<Long> recipeIdsWithAllergens);
}
