package com.templateproject.api.controller;

import com.templateproject.api.entity.Category;
import com.templateproject.api.entity.IngredientRecipe;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.exception.MissingEntityException;
import com.templateproject.api.repository.RecipesRepository;
import com.templateproject.api.service.RecipesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipesService recipesService;
    private final RecipesRepository recipesRepository;

    public RecipeController(RecipesService recipesService,
                            RecipesRepository recipesRepository) {
        this.recipesService = recipesService;
        this.recipesRepository = recipesRepository;
    }

    // Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipesService.getAllRecipes();
    }

    @GetMapping(value = "/allergens")
    public ResponseEntity<List<Recipe>> getRecipesByAllergens(@RequestParam("allergen") List<String> allergenNames) {
        List<Recipe> recipes = recipesService.getRecipesByAllergens(allergenNames);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/not-allergens")
    public ResponseEntity<List<Recipe>> getRecipesByNotAllergens(@RequestParam("allergen") List<String> allergenNames) {
        List<Recipe> recipesWithAllergens = recipesService.getRecipesByAllergens(allergenNames);
        List<Recipe> allRecipes = recipesService.getAllRecipes();
        List<Recipe> recipesWithoutAllergens = allRecipes.stream()
                .filter(recipe -> !recipesWithAllergens.contains(recipe))
                .collect(Collectors.toList());

        if (recipesWithoutAllergens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipesWithoutAllergens, HttpStatus.OK);
    }


@GetMapping(value = "/categories")
public List<Recipe> getRecipesByCategory(@RequestParam(value = "category", required = false) String categoryName) throws MissingEntityException {
    if (categoryName != null) {
        return recipesService.getRecipesByCategory(categoryName);
    } else {
        return recipesService.findAllByOnCategory(false);
    }
  }
}

