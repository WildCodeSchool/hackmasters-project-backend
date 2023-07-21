package com.templateproject.api.repository;

import com.templateproject.api.entity.ReviewsUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewsUsers, Long> {

    void deleteByRecipe_Id(Long recipeId);


}
