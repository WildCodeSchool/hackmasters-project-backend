package com.templateproject.api.controller;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.Users;
import com.templateproject.api.repository.CreateRecipeRepository;

import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/createRecipe")
public class CreateRecipeController {

    private final CreateRecipeRepository createRecipeRepository;
    private final UsersRepository usersRepository;
    private final RecipeRepository recipeRepository;


    public CreateRecipeController(CreateRecipeRepository createRecipeRepository, UsersRepository usersRepository, RecipeRepository recipeRepository) {
        this.createRecipeRepository = createRecipeRepository;
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/isCreated")
    public ResponseEntity<Boolean> isRecipeCreatedByUser(
            @RequestParam Long recipeId,
            @RequestParam Long userId
    ) {
        Optional<CreateRecipe> optionalCreateRecipe = createRecipeRepository.findByUsersIdAndRecipeId(userId, recipeId);

        return ResponseEntity.ok(optionalCreateRecipe.isPresent());
    }
    @PostMapping("/create")
    public ResponseEntity<CreateRecipe> createRecipe(@RequestParam("userId") Long userId, @RequestParam("recipeId") Long recipeId) {
        Users users = usersRepository.findById(userId).orElse(null);
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);

        if(users == null || recipe == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CreateRecipe createRecipe = new CreateRecipe();
        createRecipe.setUsers(users);
        createRecipe.setRecipe(recipe);

        createRecipeRepository.save(createRecipe);

        return new ResponseEntity<>(createRecipe, HttpStatus.CREATED);
    }

}
