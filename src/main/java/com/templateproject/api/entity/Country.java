package com.templateproject.api.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column (name = "id")
    private Integer id;
    @Column(name = "country_name")
    private String countryName;
    public Country(Integer id, String countryName, List<Recipe> recipes) {
        this.id = id;
        this.countryName = countryName;
    }
    public Country() {}
    public Integer getId() {
        return id;
    }
    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
