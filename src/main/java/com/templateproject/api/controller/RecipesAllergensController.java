package com.templateproject.api.controller;


import com.templateproject.api.entity.RecipeAllergen;
import com.templateproject.api.repository.RecipesAllergensRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipesallergens")
public class RecipesAllergensController {

    private final RecipesAllergensRepository recipesAllergensRepository;

    public RecipesAllergensController(RecipesAllergensRepository recipesAllergensRepository) {
        this.recipesAllergensRepository = recipesAllergensRepository;
    }

    @PostMapping()
    public List<RecipeAllergen> addRecipesAllergens(@RequestBody List<RecipeAllergen> recipeAllergens) {
        return recipesAllergensRepository.saveAll(recipeAllergens);
    }

}
