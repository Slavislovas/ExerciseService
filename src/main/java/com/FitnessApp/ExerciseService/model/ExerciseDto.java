package com.FitnessApp.ExerciseService.model;

import com.FitnessApp.ExerciseService.business.enums.ExerciseType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Exercise data transfer object")
public class ExerciseDto {
    @ApiModelProperty(name = "id", notes = "Id of the exercise", example = "123AAds", hidden = true)
    private String id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    @ApiModelProperty(name = "title", notes = "Title of the exercise", example = "Bench press")
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 500)
    @ApiModelProperty(name = "description", notes = "Description of the exercise", example = "Lorem ipsum...")
    private String description;

    @NotNull
    @Min(1)
    @Max(10)
    @ApiModelProperty(name = "sets", notes = "Amount of times to do the exercise", example = "5")
    private int sets;

    @NotNull
    @Min(1)
    @Max(100)
    @ApiModelProperty(name = "repsPerSet", notes = "Amount of times to do each set", example = "10")
    private int repsPerSet;

    @NotNull
    @ApiModelProperty(name = "type", notes = "Type of the exercise", example = "Strength")
    private ExerciseType type;
}
