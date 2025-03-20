package com.app.training.service;

import com.app.training.entity.Training;
import com.app.training.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    public Training saveTraining(Training training) {
        if(training.getStartDate() != null && training.getEndDate() != null) {
            if(training.getStartDate().isAfter(training.getEndDate())) {
                throw new IllegalArgumentException("Start date must be before the end date");
            }
        }
        return trainingRepository.save(training);
    }

    public Optional<Training> getTrainingById(Integer id) {
        return trainingRepository.findById(id);
    }

    public List<Training> getAllTraining() {
        return trainingRepository.findAll();
    }

}
