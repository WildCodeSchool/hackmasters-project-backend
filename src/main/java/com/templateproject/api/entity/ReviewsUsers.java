package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "review_users")
public class ReviewsUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.UserDetail.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipe;

    @Column(name = "comments")
    @JsonView(Views.UserDetail.class)
    private String comment;

    @Column(name = "rating")
    @JsonView(Views.UserDetail.class)
    private BigDecimal rating;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal  getRating() {
        return rating;
    }

    public void setRating(BigDecimal  rating) {
        this.rating = rating;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
