package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserDetail.class)
    private Long id;
    @JsonView(Views.UserDetail.class)
    private String categoryName;
    public Long getId() {
        return id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
