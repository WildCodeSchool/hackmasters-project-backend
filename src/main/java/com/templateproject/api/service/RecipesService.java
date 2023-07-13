package com.templateproject.api.service;

import com.templateproject.api.entity.Recipe;
import com.templateproject.api.exception.MissingEntityException;
import java.util.List;

public interface RecipesService {

    List<Recipe> getAllRecipes();

    List<Recipe> getRecipesByAllergens(List<String> allergenNames);

    List<Recipe> findAllByOnCategory(Boolean withoutcategory);

    List<Recipe> getRecipesByCategory(String categoryName) throws MissingEntityException;

}
