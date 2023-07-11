package com.templateproject.api.controller;

import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeService recipeService;

    // Get all recipes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
    @GetMapping("/allergens")
    public ResponseEntity<List<Recipe>> getRecipesByAllergens(@RequestParam("allergen") List<String> allergenNames) {
        List<Recipe> recipes = recipeService.getRecipesByAllergens(allergenNames);
        if (recipes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }


}
