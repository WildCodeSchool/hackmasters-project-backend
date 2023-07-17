package com.templateproject.api.controller;

import com.templateproject.api.entity.RecipeDiet;
import com.templateproject.api.repository.RecipeDietsRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipesdiets")
public class RecipesDietsController {

    private final RecipeDietsRepository recipesDietsRepository;

    public RecipesDietsController(RecipeDietsRepository recipesDietsRepository) {
        this.recipesDietsRepository = recipesDietsRepository;
    }

    @PostMapping()
    public List<RecipeDiet> addRecipesDiets(@RequestBody List<RecipeDiet> recipeDiets) {
        return recipesDietsRepository.saveAll(recipeDiets);
    }
}
