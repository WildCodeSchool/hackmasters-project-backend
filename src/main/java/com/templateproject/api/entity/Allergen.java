package com.templateproject.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "allergens")
public class Allergen {

    @Id
    private Integer id;

    @Column(name = "allergen_name")
    private String allergenName;

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
