package com.FitnessApp.ExerciseService.business.service.impl;

import com.FitnessApp.ExerciseService.business.mapper.ExerciseMapStruct;
import com.FitnessApp.ExerciseService.business.repository.ExerciseRepository;
import com.FitnessApp.ExerciseService.business.repository.model.ExerciseEntity;
import com.FitnessApp.ExerciseService.business.service.ExerciseService;
import com.FitnessApp.ExerciseService.model.ExerciseDto;
import com.FitnessApp.ExerciseService.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(groupId = "fitnessApp",
            topics = "workout-exercise",
            containerFactory = "exerciseKafkaListenerContainerFactory")
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
        ExerciseEntity exerciseEntity = exerciseEntityOptional.get();
        ExerciseEntity convertedDto = exerciseMapStruct.dtoToEntity(exerciseData);
        convertedDto.setId(exerciseId);
        convertedDto.setWorkoutId(exerciseEntity.getWorkoutId());
        return exerciseMapStruct.entityToDto(exerciseRepository.save(convertedDto));
    }

    @Override
    public void deleteExercise(String exerciseId) {
        Optional<ExerciseEntity> exerciseEntityOptional = exerciseRepository.findById(exerciseId);
        if (exerciseEntityOptional.isEmpty()){
            throw new NoSuchElementException("Exercise with id: " + exerciseId + " does not exist");
        }
        exerciseRepository.deleteById(exerciseId);
    }

    @KafkaListener(id = " ", groupId = "fitnessApp",
            topics = "workout-exercise-delete",
            containerFactory = "messageKafkaListenerContainerFactory")
    @Override
    public void deleteAllExercisesByWorkoutId(Message workoutDeletionMessage) {
        exerciseRepository.deleteAllByWorkoutId(workoutDeletionMessage.getMessage());
    }
}
