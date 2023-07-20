package com.templateproject.api.controller;

import com.templateproject.api.entity.Country;
import com.templateproject.api.repository.CountryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository CountryRepository;

    public CountryController(CountryRepository CountryRepository) {
        this.CountryRepository = CountryRepository;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return CountryRepository.findAll();
    }
}