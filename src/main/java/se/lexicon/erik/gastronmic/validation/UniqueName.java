package se.lexicon.erik.gastronmic.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface UniqueName {
	
	String message() default "This ingredient name is already in the database";
	public Class<?>[] groups() default{};
	public Class<? extends Payload>[] payload() default{};
		

}
