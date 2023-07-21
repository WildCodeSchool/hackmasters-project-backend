package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;


@Entity
@Table(name = "recipe_diets")
    public class RecipeDiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetail.class)
    private long id;
    @ManyToOne
    @JoinColumn(name = "diet_id")
    @JsonView(Views.UserDetail.class)
    private Diet diet;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipes;


    public long getId() {
        return id;
    }


    public Recipe getRecipe() {
        return recipes;
    }

    public Recipe getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe recipes) {
        this.recipes = recipes;
    }

    public void setRecipe(Recipe recipe) {
        this.recipes = recipe;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }



}
