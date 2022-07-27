package com.epam.hw_6.util;

import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.LockStatus;
import com.epam.hw_6.model.enums.Role;

public class UserTestDataUtil {
  public static final String MOCK_EMAIL = "ihor2001@gmail.com";
  public static final String MOCK_PASSWORD = "IhorBerezovskyi11!";
  public static final String MOCK_USER_NAME = "IhorBerezovskyi";
  public static final long MOCK_USER_ID = 1;

  public static User getUser() {
    return User.builder()
        .id(MOCK_USER_ID)
        .name(MOCK_USER_NAME)
        .emailAddress(MOCK_EMAIL)
        .password(MOCK_PASSWORD)
        .role(Role.USER)
        .lockStatus(LockStatus.UNLOCKED)
        .build();
  }

  public static User getLibrarian() {
    return User.builder()
        .id(MOCK_USER_ID)
        .name(MOCK_USER_NAME)
        .emailAddress(MOCK_EMAIL)
        .password(MOCK_PASSWORD)
        .role(Role.LIBRARIAN)
        .lockStatus(LockStatus.UNLOCKED)
        .build();
  }
}
