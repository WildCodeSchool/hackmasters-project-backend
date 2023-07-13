package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "allergens")

public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;
    @Column(name = "allergen_name")
    private String allergenName;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "allergens")
    @JsonIgnore
    @JsonIgnoreProperties("allergens")
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