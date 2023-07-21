package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "allergens")

public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetail.class)
    private int id;
    @Column(name = "allergen_name")
    @JsonView(Views.UserDetail.class)
    private String allergenName;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "allergens")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Recipe> recipes = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getAllergenName() {
        return allergenName;
    }
    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }
   public Set<Recipe> getRecipes() {
        return recipes;
    }


}