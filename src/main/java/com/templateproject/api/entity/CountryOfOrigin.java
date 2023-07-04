package com.templateproject.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "country_of_origin")
public class CountryOfOrigin {

    @Id
    private Integer id;

    @Column(name = "country_name")
    private String countryName;

    public CountryOfOrigin(Integer id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public CountryOfOrigin() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
