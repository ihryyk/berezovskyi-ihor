package com.epam.hw_6.controller.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidUserName implements ConstraintValidator<UserNameValidation, String> {

  private final String USER_NAME_REGEX =
      "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

  @Override
  public void initialize(UserNameValidation constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
    Pattern pattern = Pattern.compile(USER_NAME_REGEX);
    return pattern.matcher(userName).matches();
  }
}
