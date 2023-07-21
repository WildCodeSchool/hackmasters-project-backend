package com.templateproject.api.controller;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.FavoriteRecipes;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.FavoriteRecipeRepository;
import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    private final FavoriteRecipeRepository favoriteRecipeRepository;

    public FavoriteController(UserRepository userRepository, RecipeRepository recipeRepository, FavoriteRecipeRepository favoriteRecipeRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createFavoriteRecipe(@RequestParam long userId, @RequestParam long recipeId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalUser.isEmpty() || optionalRecipe.isEmpty()) {
            return ResponseEntity.badRequest().body("");
        }

        User user = optionalUser.orElse(null);
        Recipe recipe = optionalRecipe.orElse(null);

        FavoriteRecipes favoriteRecipe = new FavoriteRecipes();
        favoriteRecipe.setUser(user);
        favoriteRecipe.setRecipe(recipe);
        favoriteRecipeRepository.save(favoriteRecipe);

        return ResponseEntity.ok("");
    }
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIfFavoriteExists(@RequestParam long userId, @RequestParam long recipeId) {
        boolean favoriteExists = favoriteRecipeRepository.existsByUserIdAndRecipeId(userId, recipeId);
        return ResponseEntity.ok(favoriteExists);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFavoriteRecipe(@RequestParam long recipeId, @RequestParam long userId) {
        Optional<FavoriteRecipes> optionalFavoriteRecipe = favoriteRecipeRepository.findByRecipeIdAndUserId(recipeId, userId);

        if (optionalFavoriteRecipe.isEmpty()) {
            return ResponseEntity.badRequest().body("Favorite_recipe introuvable pour l'utilisateur donné et l'ID de recette donné.");
        }

        FavoriteRecipes favoriteRecipe = optionalFavoriteRecipe.get();
        favoriteRecipeRepository.delete(favoriteRecipe);

        return ResponseEntity.ok("Favorite_recipe supprimé avec succès.");
    }

}



