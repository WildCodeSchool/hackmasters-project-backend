package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "ingredients")
public class    Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @JsonView(Views.UserDetail.class)
  private int id;

  @Column(name = "ingredient_name")
  @JsonView(Views.UserDetail.class)
  private String ingredientName;
 public int getId() {
  return id;
 }

 public String getIngredientName() {
  return ingredientName;
 }
 public void setIngredientName(String ingredientName) {
  this.ingredientName = ingredientName;
 }
}




