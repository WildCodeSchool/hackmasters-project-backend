package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "steps")
public class Step {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "step_description")
    private String stepDescription;

    @ManyToMany
    @JoinTable(name = "steps_recipe",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe"))
    private List<Recipe> recipes;

    public Step(Integer id, Recipe recipe, String stepDescription) {
        this.id = id;
        this.recipe = recipe;
        this.stepDescription = stepDescription;
    }

    public Step() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
