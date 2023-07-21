package com.templateproject.api.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.templateproject.api.views.Views;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @JsonView(Views.UserDetail.class)
    private Integer id;
    @Column(name = "country_name")
    @JsonView(Views.UserDetail.class)
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
