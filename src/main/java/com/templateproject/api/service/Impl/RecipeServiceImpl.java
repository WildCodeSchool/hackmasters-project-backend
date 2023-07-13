package com.templateproject.api.service.Impl;

import com.templateproject.api.exception.MissingEntityException;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import com.templateproject.api.entity.Recipe;
import com.templateproject.api.repository.RecipesRepository;
import com.templateproject.api.service.RecipesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecipeServiceImpl implements RecipesService {
    private final RecipesRepository recipesRepository;
    private final EntityManager entityManager;

    public RecipeServiceImpl(RecipesRepository recipesRepository, EntityManager entityManager) {
        this.recipesRepository = recipesRepository;
        this.entityManager = entityManager;
    }

    /**
     * Retrieves all recipes with their associated diets and allergens.
     * Uses a query to fetch the recipes along with their related diets and allergens in a single database call.
     *
     * @return List of Recipe objects with associated diets and allergens
     */
    @Transactional(readOnly = true)
    public List<Recipe> getAllRecipes() {
        return entityManager.createQuery(
                        "SELECT DISTINCT r FROM Recipe r " +
                                "LEFT JOIN FETCH r.diets " +
                                "LEFT JOIN FETCH r.allergens", Recipe.class)
                .getResultList();
    }


    @Override
    public List<Recipe> getRecipesByAllergens(List<String> allergenNames) {
        return recipesRepository.findByAllergensAllergenNameIn(allergenNames);
    }

    /**
     * Retrieves all recipes based on the provided category name.
     * Throws a MissingEntityException if the category name is null or empty.
     * If the category name is valid, uses the 'findByCategoryCategoryName()' method from 'recipesRepository'
     * to retrieve all recipes belonging to the specified category.
     * Throws a MissingEntityException if no recipes are found for the specified category.
     *
     * @param categoryName The name of the category to filter the recipes
     * @return List of Recipe objects based on the specified category
     * @throws MissingEntityException If the category is not specified or no recipes are found for the category
     */
    @Override
    public List<Recipe> getRecipesByCategory(String categoryName) throws MissingEntityException {
        if (categoryName == null || categoryName.isEmpty()) {
            throw new MissingEntityException("Category is not specified.");
        } else {
            List<Recipe> recipes = recipesRepository.findByCategoryCategoryName(categoryName);
            if (recipes.isEmpty()) {
                throw new MissingEntityException("No recipes found for the specified category.");
            }
            return recipes;
        }
    }

    /**
     * Retrieves all recipes based on the 'withoutcategory' parameter.
     * If 'withoutcategory' is true, uses the 'findByCategoryIsNull()' method from 'recipesRepository'
     * to fetch all recipes without a category. Otherwise, uses the 'findAll()' method from 'recipesRepository'
     * to retrieve all recipes, regardless of their categories.
     *
     * @param withoutcategory Flag indicating whether to fetch recipes without a category
     * @return List of Recipe objects based on the specified criteria
     */
    @Override
    public List<Recipe> findAllByOnCategory(Boolean withoutcategory) {
        List<Recipe> recipes;
        Iterable<Recipe> recipesEntities;
        if (Boolean.TRUE.equals(withoutcategory)) {
            recipesEntities = recipesRepository.findByCategoryIsNull();
        } else {
            recipesEntities = recipesRepository.findAll();
        }
        recipes = StreamSupport.stream(recipesEntities.spliterator(), false)
                .collect(Collectors.toList());
        return recipes;
    }
}
