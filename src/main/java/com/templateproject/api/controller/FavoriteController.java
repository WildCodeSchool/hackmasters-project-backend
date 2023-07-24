package com.templateproject.api.controller;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.FavoriteRecipes;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.Users;
import com.templateproject.api.repository.FavoriteRecipeRepository;
import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.UsersRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final UsersRepository usersRepository;
    private final RecipeRepository recipeRepository;

    private final FavoriteRecipeRepository favoriteRecipeRepository;

    public FavoriteController(UsersRepository usersRepository, RecipeRepository recipeRepository, FavoriteRecipeRepository favoriteRecipeRepository) {
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
        this.favoriteRecipeRepository = favoriteRecipeRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createOrUpdateFavoriteRecipe(@RequestParam long userId, @RequestParam long recipeId) {
        Optional<Users> optionalUser = usersRepository.findById(userId);
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalUser.isEmpty() || optionalRecipe.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid user ID or recipe ID");
        }
        Users user = optionalUser.get();
        Recipe recipe = optionalRecipe.get();

        Optional<FavoriteRecipes> existingFavoriteRecipe = favoriteRecipeRepository.findByUsersAndRecipe(user, recipe);

        if (existingFavoriteRecipe.isPresent()) {
            // Si la relation existe, la supprimer
            favoriteRecipeRepository.delete(existingFavoriteRecipe.get());
            return ResponseEntity.ok("Favorite recipe removed");
        } else {
            // Si la relation n'existe pas, la créer
            FavoriteRecipes favoriteRecipe = new FavoriteRecipes();
            favoriteRecipe.setUser(user);
            favoriteRecipe.setRecipe(recipe);
            favoriteRecipeRepository.save(favoriteRecipe);
            return ResponseEntity.ok("Favorite recipe added");
        }
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkIfFavoriteExists(@RequestParam long userId, @RequestParam long recipeId) {
        boolean favoriteExists = favoriteRecipeRepository.existsByUsersIdAndRecipeId(userId, recipeId);
        return ResponseEntity.ok(favoriteExists);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFavoriteRecipe(@RequestParam long recipeId, @RequestParam long userId) {
        Optional<FavoriteRecipes> optionalFavoriteRecipe = favoriteRecipeRepository.findByRecipeIdAndUsersId(recipeId, userId);

        if (optionalFavoriteRecipe.isEmpty()) {
            return ResponseEntity.badRequest().body("Favorite_recipe introuvable pour l'utilisateur donné et l'ID de recette donné.");
        }

        FavoriteRecipes favoriteRecipe = optionalFavoriteRecipe.get();
        favoriteRecipeRepository.delete(favoriteRecipe);

        return ResponseEntity.ok("Favorite_recipe supprimé avec succès.");
    }

}



