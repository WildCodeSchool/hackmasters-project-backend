package com.templateproject.api.controller;


import com.templateproject.api.repository.RecipeRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/recipes")
public class RecipeController {


    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

   @GetMapping("/{slug}")
    public Recipe getRecipeById(@PathVariable String slug) {
        return recipeRepository.findBySlug(slug).orElseThrow();
    }

    @PostMapping("/add")
    public Recipe addRecipe(String recipeName, Category category, Country country, int prepTime, int cookTime, BigDecimal price, String imgUrl, String description) {
        Recipe recipe = new Recipe(recipeName, category, country, prepTime, cookTime, price, imgUrl, description);
        return recipeRepository.save(recipe);
    }

    @PutMapping("/update/{id}")
    public Recipe updateRecipe(@PathVariable Long id, String recipeName, Category category, Country country, int prepTime, int cookTime, BigDecimal price, String imgUrl, String description) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        recipe.setRecipeName(recipeName);
        recipe.setCategory(category);
        recipe.setCountry(country);
        recipe.setPrepTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setPrice(price);
        recipe.setImgUrl(imgUrl);
        recipe.setDescription(description);
        return recipeRepository.save(recipe);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecipe(@PathVariable Long id) {
        recipeRepository.deleteById(id);
    }
}
