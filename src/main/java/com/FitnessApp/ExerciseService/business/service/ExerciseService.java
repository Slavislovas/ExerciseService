package com.FitnessApp.ExerciseService.business.service;

import com.FitnessApp.ExerciseService.model.ExerciseDto;

import java.util.List;

public interface ExerciseService {
    ExerciseDto findExerciseById(String exerciseId);
    List<ExerciseDto> findAllExercises();
    ExerciseDto createExercise(ExerciseDto exercise);
    ExerciseDto updateExercise(String exerciseId, ExerciseDto exerciseData);
    void deleteExercise(String exerciseId);
}
