package com.templateproject.api.repository;

import com.templateproject.api.entity.RecipeAllergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeAllergenRepository extends JpaRepository<RecipeAllergen, Integer> {
}
