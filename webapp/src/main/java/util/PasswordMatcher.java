package util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validator.PasswordValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR})
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordMatcher
{
	String message() default "Password Mismatch!";
	Class<?>[] groups() default {}; 
    Class<? extends Payload>[] payload() default {};
}
