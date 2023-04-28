package com.FitnessApp.ExerciseService.business.repository;

import com.FitnessApp.ExerciseService.business.repository.model.ExerciseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExerciseRepository extends MongoRepository<ExerciseEntity, String> {
    void deleteAllByWorkoutId(String workoutId);
}
