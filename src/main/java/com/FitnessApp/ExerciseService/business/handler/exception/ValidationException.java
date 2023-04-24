package com.FitnessApp.ExerciseService.business.handler.exception;

import com.FitnessApp.ExerciseService.business.handler.ValidationErrorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException{
    private ValidationErrorModel validationErrorModel;

    public ValidationException(ValidationErrorModel validationErrorModel){
        super();
        this.validationErrorModel = validationErrorModel;
    }

    public ValidationException(){
        super();
        this.validationErrorModel = new ValidationErrorModel();
    }
}
