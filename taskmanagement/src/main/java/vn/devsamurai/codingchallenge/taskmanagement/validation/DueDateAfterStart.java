package vn.devsamurai.codingchallenge.taskmanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DueDateAfterStartValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DueDateAfterStart {
    String message() default "Due day must be after start day";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
