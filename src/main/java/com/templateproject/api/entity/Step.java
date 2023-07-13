package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "steps")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column(name = "step_description")
    private String stepDescription;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    public Step() {

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

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }
}