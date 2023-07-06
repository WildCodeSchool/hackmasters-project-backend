package com.templateproject.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Ingredient{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 @NotNull
 private Long id;

 @Column
 @NotBlank
 @Size(min=5, max = 10, message="The size rules are not met")
 private String name;

 public Ingredient(Long id, String name) {
  this.id = id;
  this.name = name;
 }

 public Ingredient() {

 }

 public Long getId() {
  return id;
 }


 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }
}
