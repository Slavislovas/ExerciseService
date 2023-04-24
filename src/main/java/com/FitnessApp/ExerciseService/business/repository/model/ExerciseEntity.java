package com.FitnessApp.ExerciseService.business.repository.model;

import com.FitnessApp.ExerciseService.business.enums.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("exercise")
public class ExerciseEntity {
    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("description")
    private String description;

    @Field("sets")
    private int sets;

    @Field("repsPerSet")
    private int repsPerSet;

    @Field("type")
    private ExerciseType type;
}
