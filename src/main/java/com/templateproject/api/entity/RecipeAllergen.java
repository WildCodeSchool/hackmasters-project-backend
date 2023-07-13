package com.templateproject.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_allergens")
public class RecipeAllergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "allergen_id")
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