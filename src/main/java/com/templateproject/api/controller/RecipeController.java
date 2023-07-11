package com.templateproject.api.controller;

import com.templateproject.api.entity.Recipe;
import com.templateproject.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/allergens")
    public ResponseEntity<List<Recipe>> getRecipesByAllergens(@RequestParam("allergen") List<String> allergenNames) {
        List<Recipe> recipes = recipeService.getRecipesByAllergens(allergenNames);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/not-allergens")
    public ResponseEntity<List<Recipe>> getRecipesByNotAllergens(@RequestParam("allergen") List<String> allergenNames) {
        List<Recipe> recipesWithAllergens = recipeService.getRecipesByAllergens(allergenNames);
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        List<Recipe> recipesWithoutAllergens = allRecipes.stream()
                .filter(recipe -> !recipesWithAllergens.contains(recipe))
                .collect(Collectors.toList());

        if (recipesWithoutAllergens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipesWithoutAllergens, HttpStatus.OK);
    }
}
