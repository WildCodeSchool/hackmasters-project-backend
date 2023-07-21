package com.templateproject.api.controller;

import com.templateproject.api.entity.Step;
import com.templateproject.api.repository.StepRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/steps")
public class StepController {

    final private StepRepository stepRepository;

    public StepController(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @PostMapping()
    public List<Step> addSteps(@RequestBody List<Step> steps) {
        return stepRepository.saveAll(steps);
    }
}
