package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @JsonView(Views.UserDetail.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonView(Views.UserDetail.class)
    @Column(name = "recipe_name")
    private String recipeName;
    @JsonView(Views.UserDetail.class)
    public String getRecipeSlug() {
        return recipeSlug;
    }

    public void setRecipeSlug(String recipeSlug) {
        this.recipeSlug = recipeSlug;
    }
    @JsonView(Views.UserDetail.class)
    @Column(name = "recipe_slug")
    private String recipeSlug;
    @JsonView(Views.UserDetail.class)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonView(Views.UserDetail.class)
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @JsonView(Views.UserDetail.class)
    @Column(name = "prep_time")
    private Integer prepTime;
    @JsonView(Views.UserDetail.class)
    @Column(name = "cook_time")
    private Integer cookTime;
    @JsonView(Views.UserDetail.class)
    private BigDecimal price;

    @Column(name = "imgurl")
    @JsonView(Views.UserDetail.class)
    private String imageUrl;

    @JsonView(Views.UserDetail.class)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "recipe_allergens",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "allergen_id") })
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.UserDetail.class)
    private Set<Allergen> allergens = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "recipe_diets",
            joinColumns = { @JoinColumn(name = "recipe_id") },
            inverseJoinColumns = { @JoinColumn(name = "diet_id") })
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.UserDetail.class)
    private Set<Diet> diets = new HashSet<>();


    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.UserDetail.class)
    private Set<IngredientRecipe> ingredientRecipes ;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.UserDetail.class)
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonView(Views.UserDetail.class)
    private List<ReviewsUsers> reviews = new ArrayList<>();

    public List<ReviewsUsers> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewsUsers> reviews) {
        this.reviews = reviews;
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

    public Set<IngredientRecipe> getIngredientRecipes() {
        return ingredientRecipes;
    }

    public void setIngredientRecipes(Set<IngredientRecipe> ingredientRecipes) {
        this.ingredientRecipes = ingredientRecipes;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
