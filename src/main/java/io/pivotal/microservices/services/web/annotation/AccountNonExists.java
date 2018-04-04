package io.pivotal.microservices.services.web.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import io.pivotal.microservices.services.web.validator.AccountNonExistsConstraintValidator;

/**
 * Checks whether account with a given number already exits in DB
 */
@Constraint(validatedBy = { AccountNonExistsConstraintValidator.class })
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface AccountNonExists {
	
	public String message() default "Account exists already";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
