package com.templateproject.api.repository;

import com.templateproject.api.entity.CreateRecipe;
import com.templateproject.api.entity.FavoriteRecipes;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipes, Long> {
    List<FavoriteRecipes> findByUsers(Users users);

    Optional<FavoriteRecipes> findByRecipeIdAndUsersId(long recipeId, long userId);

    boolean existsByUsersIdAndRecipeId(long userId, long recipeId);

    List<FavoriteRecipes> findByRecipe_Id(Long recipeId);

    void deleteByRecipe_Id(Long recipeId);

    Optional<FavoriteRecipes> findByUsersAndRecipe(Users users, Recipe recipe);
}
