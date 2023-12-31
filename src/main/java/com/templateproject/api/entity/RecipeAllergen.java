package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;

@Entity
@Table(name = "recipe_allergens")
public class RecipeAllergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetail.class)
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonView(Views.UserDetail.class)
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "allergen_id")
    @JsonView(Views.UserDetail.class)
    private Allergen allergen;

    // Getters and setters

    public int getId() {
        return id;
    }
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Allergen getAllergen() {
        return allergen;
    }

    public void setAllergen(Allergen allergen) {
        this.allergen = allergen;
    }
}