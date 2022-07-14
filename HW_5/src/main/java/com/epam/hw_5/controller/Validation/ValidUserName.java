package com.epam.hw_5.controller.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ValidUserName implements ConstraintValidator<UserNameValidation, String> {
  private final String USER_NAME_REGEX =
      "^([A-ZА-ЯЄІЇ])((['][A-Zа-яєії])?)([a-zа-яєії]+)([-][A-ZА-ЯІЇ]((['][A-Zа-яєії])?)[a-zа-яєії']+)?";

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
