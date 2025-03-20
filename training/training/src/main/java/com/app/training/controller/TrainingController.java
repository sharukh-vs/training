package com.app.training.controller;

import com.app.training.entity.Training;
import com.app.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trainings")
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @PostMapping
    public Training createTraining(@RequestBody Training training) {
        return trainingService.saveTraining(training);
    }

    @GetMapping
    public List<Training> getAllTrainings(){
        return trainingService.getAllTraining();
    }

    @GetMapping("/{id}")
    public Optional<Training> getTrainingById(@PathVariable Integer id) {
        return trainingService.getTrainingById(id);
    }

}
