package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_recipe", nullable = false)
    private Long id_recipe;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Integer id;

    @Column(name = "recipe_name")
    @NotBlank
    private String recipeName;

    @Column(name = "recipe_type")
    @NotBlank
    private String recipeType;


    @ManyToOne
    @JoinColumn(name = "country_of_origin_id")
    @NotBlank
    private CountryOfOrigin countryOfOrigin;

    @Column(name = "pre_time")
    @NotBlank
    private Integer preTime;

    @Column(name = "cook_time")
    @NotBlank
    private Integer cookTime;

    @Column
    @NotBlank
    private BigDecimal price;

    @Column(name = "imgUrl")

    private String description;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "allergen_id")
    private Allergen allergen;

    public Long getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Long id_recipe) {
        this.id_recipe = id_recipe;
    }

    public Recipe(Integer id, String recipeName, String recipeType, CountryOfOrigin countryOfOrigin, Integer preTime, Integer cookTime, BigDecimal price, String description, Diet diet, Allergen allergen) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.countryOfOrigin = countryOfOrigin;
        this.preTime = preTime;
        this.cookTime = cookTime;
        this.price = price;
        this.description = description;
        this.diet = diet;
        this.allergen = allergen;
    }

    public Recipe() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public CountryOfOrigin getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(CountryOfOrigin countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Integer getPreTime() {
        return preTime;
    }

    public void setPreTime(Integer preTime) {
        this.preTime = preTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Allergen getAllergen() {
        return allergen;
    }

    public void setAllergen(Allergen allergen) {
        this.allergen = allergen;
    }
}
