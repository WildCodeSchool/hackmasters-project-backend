    package com.templateproject.api.entity;

    import jakarta.persistence.*;



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

     public String getIngredientName() {
      return ingredientName;
     }
     public void setIngredientName(String ingredientName) {
      this.ingredientName = ingredientName;
     }
    }




