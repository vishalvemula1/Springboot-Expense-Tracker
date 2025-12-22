package dev.v.expensetracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = NotBlankIfPresent.NotBlankIfPresentValidator.class)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface NotBlankIfPresent {

    String message() default "{validation.notBlankIfPresent}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class NotBlankIfPresentValidator implements ConstraintValidator<NotBlankIfPresent, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value == null || !value.trim().isEmpty();
        }
    }
}