package com.FitnessApp.ExerciseService.business.service.impl;

import com.FitnessApp.ExerciseService.business.mapper.ExerciseMapStruct;
import com.FitnessApp.ExerciseService.business.repository.ExerciseRepository;
import com.FitnessApp.ExerciseService.business.repository.model.ExerciseEntity;
import com.FitnessApp.ExerciseService.business.service.ExerciseService;
import com.FitnessApp.ExerciseService.model.ExerciseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapStruct exerciseMapStruct;

    @Override
    public ExerciseDto findExerciseById(String exerciseId) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(exerciseId);
        if (exerciseEntityOptional.isEmpty()){
            throw new NoSuchElementException("Exercise with id: " + exerciseId + " does not exist");
        }
        return exerciseMapStruct.entityToDto(exerciseEntityOptional.get());
    }

    @Override
    public List<ExerciseDto> findAllExercises() {
        return exerciseRepository.findAll().stream().map(exerciseMapStruct::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exercise) {
        ExerciseEntity exerciseEntity = exerciseMapStruct.dtoToEntity(exercise);
        return exerciseMapStruct.entityToDto(exerciseRepository.save(exerciseEntity));
    }

    @Override
    public ExerciseDto updateExercise(String exerciseId, ExerciseDto exerciseData) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(exerciseId);
        if (exerciseEntityOptional.isEmpty()){
            throw new NoSuchElementException("Exercise with id: " + exerciseId + " does not exist");
        }
        ExerciseEntity exerciseEntity = exerciseMapStruct.dtoToEntity(exerciseData);
        exerciseEntity.setId(exerciseId);
        return exerciseMapStruct.entityToDto(exerciseRepository.save(exerciseEntity));
    }

    @Override
    public void deleteExercise(String exerciseId) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(exerciseId);
        if (exerciseEntityOptional.isEmpty()){
            throw new NoSuchElementException("Exercise with id: " + exerciseId + " does not exist");
        }
        exerciseRepository.deleteById(exerciseId);
    }
}
