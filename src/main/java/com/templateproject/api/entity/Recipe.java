package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column(name = "recipe_name")
    private String recipeName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "cook_time")
    private Integer cookTime;

    @Column (name = "price")
    private BigDecimal price;

    @Column(name = "imgurl")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "recipe_allergens",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "allergen_id")})
    private Set<Allergen> allergens = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "recipe_diets",
            joinColumns = {@JoinColumn(name = "recipe_id")},
            inverseJoinColumns = {@JoinColumn(name = "diet_id")})
    @JsonIgnoreProperties("recipes")
    private Set<Diet> diets = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<IngredientRecipe> ingredientRecipes;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Step> steps;

    public Recipe() {

    }

    public Recipe(Recipe recipesEntity) {
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Set<IngredientRecipe> getIngredientRecipes() {
        return ingredientRecipes;
    }

    public Set<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<Allergen> allergens) {
        this.allergens = allergens;
    }

    public Set<Diet> getDiets() {
        return diets;
    }

    public void setDiets(Set<Diet> diets) {
        this.diets = diets;
    }


    public long getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipe(long id, String recipeName, Category category, Country country, Integer prepTime, Integer cookTime, BigDecimal price, String imageUrl, String description, Set<Allergen> allergens, Set<Diet> diets, Set<IngredientRecipe> ingredientRecipes, List<Step> steps) {
        this.id = id;
        this.recipeName = recipeName;
        this.category = category;
        this.country = country;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.allergens = allergens;
        this.diets = diets;
        this.ingredientRecipes = ingredientRecipes;
        this.steps = steps;
    }
}
