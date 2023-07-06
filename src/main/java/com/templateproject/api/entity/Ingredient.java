package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "ingredients")
public class Ingredient {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 @Column(name = "ingredient_name")
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




