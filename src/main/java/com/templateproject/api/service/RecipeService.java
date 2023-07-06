package com.templateproject.api.service;

import com.templateproject.api.Exception.CustomeFieldValidationException;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.orElse(null);
    }

    public Recipe createRecipe(Recipe recipe) throws CustomeFieldValidationException {
        if (recipe.getRecipeName() == null || recipe.getRecipeName().isEmpty()) {
            throw new CustomeFieldValidationException("Le nom de la recette est requis.", "recipeName");
        }


        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) throws CustomeFieldValidationException {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();
            existingRecipe.setRecipeName(updatedRecipe.getRecipeName());
            existingRecipe.setCategory(updatedRecipe.getCategory());
            existingRecipe.setCountry(updatedRecipe.getCountry());
            existingRecipe.setPrepTime(updatedRecipe.getPrepTime());
            existingRecipe.setCookTime(updatedRecipe.getCookTime());
            existingRecipe.setPrice(updatedRecipe.getPrice());
            existingRecipe.setImgUrl(updatedRecipe.getImgUrl());
            existingRecipe.setDescription(updatedRecipe.getDescription());
            return recipeRepository.save(existingRecipe);
        } else {
            throw new CustomeFieldValidationException("Recipe not found.", "id");
        }
    }
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
