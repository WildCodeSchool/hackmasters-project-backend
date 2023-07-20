package com.templateproject.api.controller;

import com.templateproject.api.entity.IngredientRecipe;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
final private RecipeRepository recipeRepository;
final private IngredientRecipeRepository IngredientRecipeRepository;
final private RecipesAllergensRepository recipesAllergensRepository;
final private RecipeDietsRepository recipesDietsRepository;

final private StepRepository stepRepository;

public RecipeController(RecipeRepository recipeRepository,
                        IngredientRecipeRepository IngredientRecipeRepository,
                        RecipesAllergensRepository recipesAllergensRepository,
                        RecipeDietsRepository recipesDietsRepository,
                        StepRepository stepRepository)
    {
        this.recipeRepository = recipeRepository;
        this.IngredientRecipeRepository = IngredientRecipeRepository;
        this.recipesAllergensRepository = recipesAllergensRepository;
        this.recipesDietsRepository = recipesDietsRepository;
        this.stepRepository = stepRepository;
    }
    // Get all recipes

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{recipeSlug}")
    public ResponseEntity<Recipe> searchRecipesByExactName(@PathVariable("recipeSlug") String recipeName) {
        Recipe recipes = recipeRepository.findByRecipeSlugIgnoreCase(recipeName);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/Slug")
    public ResponseEntity<List<Recipe>> searchRecipesByPartialName(@RequestParam("recipeSlug") String recipeName) {
        List<Recipe> recipes = recipeRepository.findByRecipeSlugContainingIgnoreCase(recipeName);
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
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> countryNames,
            @RequestParam(required = false) List<String> categoryNames,
            @RequestParam(required = false) List<String> ingredientNames,
            @RequestParam(required = false) List<String> dietNames,
            @RequestParam(required = false) List<String> allergenNames
    ) {
        List<Recipe> recipes;

        if (countryNames != null && !countryNames.isEmpty() && categoryNames != null && !categoryNames.isEmpty()) {
            recipes = recipeRepository.findByCountryCountryNameInIgnoreCaseAndCategoryCategoryNameInIgnoreCase(countryNames, categoryNames);
        } else if (countryNames != null && !countryNames.isEmpty()) {
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
        } else {
            recipes = recipeRepository.findAll();
        }

        if (allergenNames != null && !allergenNames.isEmpty()) {
            List<Recipe> recipesWithAllergens = recipeRepository.findByAllergensAllergenNameInIgnoreCase(allergenNames);
            List<Long> recipeIdsWithAllergens = recipesWithAllergens.stream().map(Recipe::getId).collect(Collectors.toList());
            recipes = recipes.stream()
                    .filter(recipe -> !recipeIdsWithAllergens.contains(recipe.getId()))
                    .collect(Collectors.toList());
        }
        if (dietNames != null && !dietNames.isEmpty()) {
            recipes = recipes.stream()
                    .filter(recipe -> recipe.getDiets().stream()
                            .anyMatch(diet -> dietNames.contains(diet.getDietName().toLowerCase())))
                    .collect(Collectors.toList());
        }

        if (query != null && !query.isEmpty()) {
            String lowerCaseQuery = query.toLowerCase();
            recipes = recipes.stream()
                    .filter(recipe -> recipe.getRecipeName().toLowerCase().contains(lowerCaseQuery) ||
                            recipe.getIngredientRecipes().stream()
                                    .anyMatch(ingredientRecipe -> ingredientRecipe.getIngredient().getIngredientName().toLowerCase().contains(lowerCaseQuery)))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(recipes);
    }

    @PostMapping()
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = recipeRepository.save(recipe);
        return ResponseEntity.ok(newRecipe);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipeById(@PathVariable("recipeId") long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}


