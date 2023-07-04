package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Steps")
public class Diet {

    @Id
    private Integer id;

    @ManyToMany
    @JoinTable(name = "diet_recipe",
                joinColumns = @JoinColumn(name = "diet_id"),
                inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List <Recipe> recipes;

    @Column(name = "step_description")
    private String stepDescription;

    public Diet(Integer id, List<Recipe> recipes, String stepDescription) {
        this.id = id;
        this.recipes = recipes;
        this.stepDescription = stepDescription;
    }

    public Diet() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}
