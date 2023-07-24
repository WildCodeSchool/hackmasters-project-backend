package com.templateproject.api.repository;

import com.templateproject.api.entity.CreateRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateRecipeRepository extends JpaRepository<CreateRecipe, Long> {

    void deleteByRecipe_Id(Long recipeId);
    Optional<CreateRecipe> findByUsersIdAndRecipeId(Long userId, Long recipeId);
}
