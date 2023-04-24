package com.FitnessApp.ExerciseService.web.controller;

import com.FitnessApp.ExerciseService.business.handler.ValidationErrorModel;
import com.FitnessApp.ExerciseService.business.handler.exception.ValidationException;
import com.FitnessApp.ExerciseService.business.service.ExerciseService;
import com.FitnessApp.ExerciseService.model.ExerciseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @ApiOperation(value = "Finds exercise by id")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Request successful"),
      @ApiResponse(code = 404, message = "Exercise not found")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<ExerciseDto> findExerciseById(@PathVariable("id") String exerciseId){
        return ResponseEntity.ok(exerciseService.findExerciseById(exerciseId));
    }

    @ApiOperation(value = "Finds all existing exercises")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful"),
    })
    @GetMapping("/find/all")
    public ResponseEntity<List<ExerciseDto>> findAllExercises(){
        return ResponseEntity.ok(exerciseService.findAllExercises());
    }

    @ApiOperation(value = "Creates a new exercise")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/create")
    public ResponseEntity<ExerciseDto> createExercise(@Valid @RequestBody ExerciseDto exercise, BindingResult bindingResult){
        validateFields(bindingResult);
        return ResponseEntity.ok(exerciseService.createExercise(exercise));
    }

    @ApiOperation(value = "Edits an existing exercise's data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Exercise not found")
    })
    @PutMapping("/edit/{id}")
    public ResponseEntity<ExerciseDto> updateExercise(@PathVariable("id") String exerciseId,
                                                      @Valid @RequestBody ExerciseDto exerciseData,
                                                      BindingResult bindingResult){
        validateFields(bindingResult);
        return ResponseEntity.ok(exerciseService.updateExercise(exerciseId, exerciseData));
    }

    @ApiOperation(value = "Deletes an existing exercise")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request successful"),
            @ApiResponse(code = 404, message = "Exercise not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable("id") String exerciseId){
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok().build();
    }

    private static void validateFields(BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            ValidationErrorModel validationErrorModel = new ValidationErrorModel();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                validationErrorModel.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new ValidationException(validationErrorModel);
        }
    }
}
