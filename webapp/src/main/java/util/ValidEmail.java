package util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validator.EmailValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD})
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail
{
	String message() default "Invalid Email";
	Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
