package com.templateproject.api.controller;


import com.templateproject.api.entity.Diet;
import com.templateproject.api.repository.DietRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diets")
public class DietController {

    final private DietRepository dietRepository;

    public DietController(DietRepository dietRepository) {
        this.dietRepository = dietRepository;
    }

    @GetMapping
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }
}
