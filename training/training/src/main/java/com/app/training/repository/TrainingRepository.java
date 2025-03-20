package com.app.training.repository;

import com.app.training.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
}
