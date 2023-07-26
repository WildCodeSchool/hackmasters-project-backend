package com.templateproject.api.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "favorite_recipes")
public class FavoriteRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public FavoriteRecipes() {

    }

    public long getId() {
        return id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
