package vn.devsamurai.codingchallenge.taskmanagement.validation;

import vn.devsamurai.codingchallenge.taskmanagement.dto.TaskRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DueDateAfterStartValidator implements ConstraintValidator<DueDateAfterStart, TaskRequestDto> {
    @Override
    public void initialize(DueDateAfterStart constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TaskRequestDto taskRequestDto, ConstraintValidatorContext constraintValidatorContext) {
        if (taskRequestDto.getStartDate() == null || taskRequestDto.getDueDate() == null) {
            // If either startDay or dueDay is null, skip validation
            return true;
        }

        return taskRequestDto.getDueDate().isAfter(taskRequestDto.getStartDate());
    }
}
