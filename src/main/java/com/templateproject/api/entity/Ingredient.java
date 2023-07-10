package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "ingredient_name")
  private String ingredientName;
 public long getId() {
  return id;
 }
 public void setId(long id) {
  this.id = id;
 }
 public String getIngredientName() {
  return ingredientName;
 }
 public void setIngredientName(String ingredientName) {
  this.ingredientName = ingredientName;
 }
}




