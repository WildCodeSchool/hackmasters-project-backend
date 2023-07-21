package com.templateproject.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.entity.FavoriteRecipes;
import com.templateproject.api.entity.IngredientRecipe;
import com.templateproject.api.entity.Recipe;

import com.templateproject.api.entity.User;
import com.templateproject.api.repository.*;
import com.templateproject.api.views.Views;
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

final private CreateRecipeRepository createRecipeRepository;

final private ReviewRepository reviewRepository;

final private StepRepository stepRepository;

final private FavoriteRecipeRepository favoriteRecipeRepository;

final private UserRepository userRepository;

public RecipeController(RecipeRepository recipeRepository,
                        IngredientRecipeRepository IngredientRecipeRepository,
                        RecipesAllergensRepository recipesAllergensRepository,
                        RecipeDietsRepository recipesDietsRepository,
                        CreateRecipeRepository createRecipeRepository, ReviewRepository reviewRepository, StepRepository stepRepository, FavoriteRecipeRepository favoriteRecipeRepository, UserRepository userRepository)
    {
        this.recipeRepository = recipeRepository;
        this.IngredientRecipeRepository = IngredientRecipeRepository;
        this.recipesAllergensRepository = recipesAllergensRepository;
        this.recipesDietsRepository = recipesDietsRepository;
        this.createRecipeRepository = createRecipeRepository;
        this.reviewRepository = reviewRepository;
        this.stepRepository = stepRepository;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    @JsonView(Views.UserDetail.class)
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/{recipeSlug}")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<Recipe> searchRecipesByExactName(@PathVariable("recipeSlug") String recipeName) {
        Recipe recipes = recipeRepository.findByRecipeSlugIgnoreCase(recipeName);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/Slug")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByPartialName(@RequestParam("recipeSlug") String recipeName) {
        List<Recipe> recipes = recipeRepository.findByRecipeSlugContainingIgnoreCase(recipeName);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/country")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByCountryNames(@RequestParam List<String> countryNames) {
        List<Recipe> recipes = recipeRepository.findByCountryCountryNameInIgnoreCase(countryNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/category")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByCategoryNames(@RequestParam List<String> categoryNames) {
        List<Recipe> recipes = recipeRepository.findByCategoryCategoryNameInIgnoreCase(categoryNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/ingredients")
    @JsonView(Views.UserDetail.class)
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
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByDietNames(@RequestParam List<String> dietNames) {
        List<Recipe> recipes = recipeRepository.findByDietsDietNameInIgnoreCase(dietNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/Allergen")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByAllergenNames(@RequestParam List<String> allergenNames) {
        List<Recipe> recipes = recipeRepository.findByAllergensAllergenNameInIgnoreCase(allergenNames);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping("/search")
    @JsonView(Views.UserDetail.class)
    public ResponseEntity<List<Recipe>> searchRecipesByCriteria(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) List<String> countryNames,
            @RequestParam(required = false) List<String> categoryNames,
            @RequestParam(required = false) List<String> ingredientNames,
            @RequestParam(required = false) List<String> dietNames,
            @RequestParam(required = false) List<String> allergenNames,
            @RequestParam(required = false) Long userId
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


        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                List<FavoriteRecipes> userFavorites = favoriteRecipeRepository.findByUser(user);
                List<Recipe> favoriteRecipes = userFavorites.stream()
                        .map(FavoriteRecipes::getRecipe)
                        .collect(Collectors.toList());
                recipes.retainAll(favoriteRecipes);
            }
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

    @Transactional
    @DeleteMapping("/additional/{recipeId}")
    public ResponseEntity<?> deleteFavoriteByRecipeId(@PathVariable Long recipeId) {
        reviewRepository.deleteByRecipe_Id(recipeId);
        createRecipeRepository.deleteByRecipe_Id(recipeId);
        favoriteRecipeRepository.deleteByRecipe_Id(recipeId);
        return ResponseEntity.ok().build();
    }

}


