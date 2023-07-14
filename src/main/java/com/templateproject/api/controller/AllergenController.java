package com.templateproject.api.controller;

import com.templateproject.api.entity.Allergen;
import com.templateproject.api.repository.AllergenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/allergens")
public class AllergenController {

    final private AllergenRepository allergenRepository;

    public AllergenController(AllergenRepository allergenRepository) {
        this.allergenRepository = allergenRepository;
    }

    @GetMapping
    public List<Allergen> getAllAllergens() {
        return allergenRepository.findAll();
    }
}
