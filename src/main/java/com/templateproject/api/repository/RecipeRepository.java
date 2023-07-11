package com.templateproject.api.repository;

import com.templateproject.api.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByAllergensAllergenNameIn(List<String> allergenNames);

}
