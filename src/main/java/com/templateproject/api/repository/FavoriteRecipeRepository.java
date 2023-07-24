package com.templateproject.api.repository;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.FavoriteRecipes;
import com.templateproject.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipes, Long> {
    List<FavoriteRecipes> findByUser(User user);

    Optional<FavoriteRecipes> findByRecipeIdAndUserId(long recipeId, long userId);

    boolean existsByUserIdAndRecipeId(long userId, long recipeId);

    List<FavoriteRecipes> findByRecipe_Id(Long recipeId);

    void deleteByRecipe_Id(Long recipeId);
}
