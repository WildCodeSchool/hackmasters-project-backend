package com.templateproject.api.controller;

import com.templateproject.api.entity.Recipe;
import com.templateproject.api.entity.ReviewsUsers;
import com.templateproject.api.entity.Users;
import com.templateproject.api.repository.RecipeRepository;
import com.templateproject.api.repository.ReviewRepository;
import com.templateproject.api.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewRepository reviewRepository;

    private  final UsersRepository usersRepository;
    private final RecipeRepository recipeRepository;


    public ReviewController(ReviewRepository reviewRepository, UsersRepository usersRepository, RecipeRepository recipeRepository) {
        this.reviewRepository = reviewRepository;
        this.usersRepository = usersRepository;
        this.recipeRepository = recipeRepository;
    }


    @PostMapping()
    public ResponseEntity<ReviewsUsers> addReview(@RequestBody ReviewsUsers review) {
        ReviewsUsers savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }


}
