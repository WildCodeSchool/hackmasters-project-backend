package com.templateproject.api.controller;

import com.templateproject.api.entity.Ingredient;
import com.templateproject.api.entity.IngredientRecipe;
import com.templateproject.api.repository.IngredientRecipeRepository;
import com.templateproject.api.repository.IngredientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredients-recipe")
public class IngredientsRecipeController {

    private final IngredientRecipeRepository ingredientRecipeRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientsRecipeController(IngredientRecipeRepository ingredientRecipeRepository, IngredientRepository ingredientRepository) {
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @PostMapping()
    public ResponseEntity<List<IngredientRecipe>> addIngredientRecipes(@RequestBody List<IngredientRecipe> ingredientRecipes) {
        List<IngredientRecipe> savedIngredientRecipes = new ArrayList<>();

        for (IngredientRecipe ingredientRecipe : ingredientRecipes) {
            Ingredient ingredient = ingredientRepository.findByIngredientName(ingredientRecipe.getIngredient().getIngredientName());
            if (ingredient == null) {
                ingredient = ingredientRepository.save(ingredientRecipe.getIngredient());
            }
            ingredientRecipe.setIngredient(ingredient);
            savedIngredientRecipes.add(ingredientRecipeRepository.save(ingredientRecipe));
        }

        return ResponseEntity.ok(savedIngredientRecipes);
    }

}