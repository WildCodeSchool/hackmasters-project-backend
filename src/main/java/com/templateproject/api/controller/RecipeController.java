package com.templateproject.api.controller;

import com.templateproject.api.entity.IngredientRecipe;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.IngredientRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRecipeRepository IngredientRecipeRepository;

    // Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{recipeName}")
    public ResponseEntity<List<Recipe>> searchRecipesByExactName(@PathVariable("recipeName") String recipeName) {
        List<Recipe> recipes = recipeRepository.findByRecipeNameIgnoreCase(recipeName);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping ("/name")
    public ResponseEntity<List<Recipe>> searchRecipesByPartialName(@RequestParam("recipeName") String recipeName) {
        List<Recipe> recipes = recipeRepository.findByRecipeNameContainingIgnoreCase(recipeName);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/country")
    public ResponseEntity<List<Recipe>> searchRecipesByCountryNames(@RequestParam List<String> countryNames) {
        List<Recipe> recipes = recipeRepository.findByCountryCountryNameInIgnoreCase(countryNames);
        return ResponseEntity.ok(recipes);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Recipe>> searchRecipesByCategoryNames(@RequestParam List<String> categoryNames) {
        List<Recipe> recipes = recipeRepository.findByCategoryCategoryNameInIgnoreCase(categoryNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Recipe>> searchRecipesByIngredientNames(@RequestParam List<String> ingredientNames) {
        List<IngredientRecipe> ingredientRecipes = new ArrayList<>();
        for (String ingredientName : ingredientNames) {
            List<IngredientRecipe> partialMatches = IngredientRecipeRepository.findByIngredientIngredientNameContainingIgnoreCase(ingredientName);
            ingredientRecipes.addAll(partialMatches);
        }
        List<Recipe> recipes = ingredientRecipes.stream().map(IngredientRecipe::getRecipe).collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }
    @GetMapping("/Diet")
    public ResponseEntity<List<Recipe>> searchRecipesByDietNames(@RequestParam List<String> dietNames) {
        List<Recipe> recipes = recipeRepository.findByDietsDietNameInIgnoreCase(dietNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/Allergen")
    public ResponseEntity<List<Recipe>> searchRecipesByAllergenNames(@RequestParam List<String> allergenNames) {
        List<Recipe> recipes = recipeRepository.findByAllergensAllergenNameInIgnoreCase(allergenNames);
        return ResponseEntity.ok(recipes);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipesByCriteria(
            @RequestParam(required = false) List<String> countryNames,
            @RequestParam(required = false) List<String> categoryNames,
            @RequestParam(required = false) List<String> ingredientNames,
            @RequestParam(required = false) List<String> dietNames,
            @RequestParam(required = false) List<String> allergenNames
    ) {
        List<Recipe> recipes;

        if (countryNames != null && !countryNames.isEmpty()) {
            recipes = recipeRepository.findByCountryCountryNameInIgnoreCase(countryNames);
        } else if (categoryNames != null && !categoryNames.isEmpty()) {
            recipes = recipeRepository.findByCategoryCategoryNameInIgnoreCase(categoryNames);
        } else if (ingredientNames != null && !ingredientNames.isEmpty()) {
            List<IngredientRecipe> ingredientRecipes = new ArrayList<>();
            for (String ingredientName : ingredientNames) {
                List<IngredientRecipe> partialMatches = IngredientRecipeRepository.findByIngredientIngredientNameContainingIgnoreCase(ingredientName);
                ingredientRecipes.addAll(partialMatches);
            }
            recipes = ingredientRecipes.stream().map(IngredientRecipe::getRecipe).collect(Collectors.toList());
        } else if (dietNames != null && !dietNames.isEmpty()) {
            recipes = recipeRepository.findByDietsDietNameInIgnoreCase(dietNames);
        } else if (allergenNames != null && !allergenNames.isEmpty()) {
            recipes = recipeRepository.findByAllergensAllergenNameInIgnoreCase(allergenNames);
        } else {
            recipes = new ArrayList<>(); // Return empty list if no criteria provided
        }

        return ResponseEntity.ok(recipes);
    }
}

