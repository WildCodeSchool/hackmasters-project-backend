package com.templateproject.api.controller;

import com.templateproject.api.Exception.CustomeFieldValidationException;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getAllRecipes(Model model) {
        List<Recipe> recipes = (List<Recipe>) recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable("id") Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "create-recipe";
    }

    @PostMapping("/create")
    public String createRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create-recipe";
        }
        try {
            recipeService.createRecipe(recipe);
            return "redirect:/recipes";
        } catch (CustomeFieldValidationException e) {
            bindingResult.rejectValue(e.getFieldName(), null, e.getMessage());
            return "create-recipe";
        }
    }
    @PutMapping("/{id}")
    public String updateRecipe(@PathVariable("id") Long id, @Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-recipe";
        }

        try {
            Recipe updatedRecipe = recipeService.updateRecipe(id, recipe);
            return "redirect:/recipes/" + updatedRecipe.getId();
        } catch (CustomeFieldValidationException e) {
            bindingResult.rejectValue(e.getFieldName(), null, e.getMessage());
            return "edit-recipe";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes";
    }
}

