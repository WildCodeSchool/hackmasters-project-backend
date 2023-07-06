package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    private Integer id;

    @Column(name = "country_name")
    private String countryName;


    @OneToMany (mappedBy = "country" )
    private List<Recipe> recipes;

    public Country(Integer id, String countryName, List<Recipe> recipes) {
        this.id = id;
        this.countryName = countryName;
        this.recipes = recipes;
    }

    public Country() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
