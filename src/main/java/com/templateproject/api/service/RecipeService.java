package com.templateproject.api.service;

import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getRecipesByAllergens(List<String> allergenNames) {
        return recipeRepository.findByAllergensAllergenNameIn(allergenNames);
    }
}
