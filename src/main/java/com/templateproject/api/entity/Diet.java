package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "diets")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "diet_name")
    private String dietName;

@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "diets")
@JsonIgnore
private Set<Recipe> recipes = new HashSet<>();
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDietName() {
        return dietName;
    }
    public void setDietName(String dietName) {
        this.dietName = dietName;
    }
    public Set<Recipe> getRecipes() {
        return recipes;
    }
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    public Diet() {
    }
}

