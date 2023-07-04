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
    private Integer id_recipe;


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
    private String imgUrl;
    private String description;

    @ManyToMany
    @JoinColumn(name = "recipes")
    private List<Allergen> allergens;


    @ManyToMany(mappedBy = "recipes")
    private List<Diet> diets;

    public Recipe(Integer id_recipe, String recipeName, String recipeType, CountryOfOrigin countryOfOrigin, Integer preTime, Integer cookTime, BigDecimal price, String description, List<Allergen> allergens, List<Diet> diets) {
        this.id_recipe = id_recipe;
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.countryOfOrigin = countryOfOrigin;
        this.preTime = preTime;
        this.cookTime = cookTime;
        this.price = price;
        this.description = description;
        this.allergens = allergens;
        this.diets = diets;
    }

    public Recipe() {

    }

    public Integer getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(Integer id_recipe) {
        this.id_recipe = id_recipe;
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

    public List<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<Allergen> allergens) {
        this.allergens = allergens;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }
}
