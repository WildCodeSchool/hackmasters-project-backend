package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "allergens")
public class Allergen {

    @Id
    private Integer id;

    @Column(name = "allergen_name")
    private String allergenName;

    @ManyToMany
    @JoinTable(name = "allergen_id_recipe",
            joinColumns = @JoinColumn(name = "allergen_id"),
            inverseJoinColumns = @JoinColumn(name = "id_recipe"))
    private List<Recipe> recipes;

    public Allergen(Integer id, String allergenName) {
        this.id = id;
        this.allergenName = allergenName;
    }

    public Allergen() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAllergenName() {
        return allergenName;
    }

    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }
}
