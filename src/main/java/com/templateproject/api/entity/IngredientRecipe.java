package com.templateproject.api.entity;

import jakarta.persistence.*;

@Entity
public class IngredientRecipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public IngredientRecipe(Integer id, Recipe recipe, Ingredient ingredient) {
        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public IngredientRecipe() {

    }

    public Integer getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
