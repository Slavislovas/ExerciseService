package com.FitnessApp.ExerciseService.business.mapper;

import com.FitnessApp.ExerciseService.business.repository.model.ExerciseEntity;
import com.FitnessApp.ExerciseService.model.ExerciseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapStruct {
    ExerciseEntity dtoToEntity(ExerciseDto dto);
    ExerciseDto entityToDto(ExerciseEntity entity);
}
