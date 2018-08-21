package util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validator.TextFieldValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD})
@Constraint(validatedBy = TextFieldValidator.class)
@Documented
public @interface NotEmptyOrNull
{
	String message() default "Field cannot be blank!";
	Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
