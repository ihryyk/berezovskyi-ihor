package com.epam.hw_6.controller.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidUserName.class)
public @interface UserNameValidation {

  String message() default "Invalid user name";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
