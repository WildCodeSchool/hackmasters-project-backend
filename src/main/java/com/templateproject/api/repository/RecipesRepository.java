package com.templateproject.api.repository;

import com.templateproject.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByCategoryIsNull();
    List<Recipe> findByAllergensAllergenNameIn(List<String> allergenNames);
    List<Recipe> findByCategoryCategoryName(String categoryName);
}
