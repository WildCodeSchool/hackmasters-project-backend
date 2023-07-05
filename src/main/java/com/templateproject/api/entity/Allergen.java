package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "allergens")

public class Allergen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "allergen_name")
    private String allergenName;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllergenName() {
        return allergenName;
    }

    public void setAllergenName(String allergenName) {
        this.allergenName = allergenName;
    }
}