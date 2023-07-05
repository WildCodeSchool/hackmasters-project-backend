package com.templateproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public Optional<Recipe> findBySlug(String slug);
    Public List<Product> findAllRecipes(Recipe recipe);
}
