package com.templateproject.api.controller;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.User;
import com.templateproject.api.repository.CreateRecipeRepository;

import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/createRecipe")
public class CreateRecipeController {

    private final CreateRecipeRepository createRecipeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    public CreateRecipeController(CreateRecipeRepository createRecipeRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.createRecipeRepository = createRecipeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/isCreated")
    public ResponseEntity<Boolean> isRecipeCreatedByUser(
            @RequestParam Long recipeId,
            @RequestParam Long userId
    ) {
        Optional<CreateRecipe> optionalCreateRecipe = createRecipeRepository.findByUserIdAndRecipeId(userId, recipeId);

        return ResponseEntity.ok(optionalCreateRecipe.isPresent());
    }
    @PostMapping("/create")
    public ResponseEntity<CreateRecipe> createRecipe(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId) {
        User user = userRepository.findById(userId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if(user == null || recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CreateRecipe createRecipe = new CreateRecipe();
        createRecipe.setUser(user);
        createRecipe.setRecipe(recipe);

        createRecipeRepository.save(createRecipe);

        return new ResponseEntity<>(createRecipe, HttpStatus.CREATED);
    }

}
