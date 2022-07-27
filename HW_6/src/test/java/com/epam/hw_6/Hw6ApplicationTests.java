package com.epam.hw_6;

import com.epam.hw_6.controller.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = Hw6Application.class)
class Hw6ApplicationTests {

  @Value("http://localhost:${local.server.port}/user/")
  private String baseUrl;

  @Value("${test.user.email}")
  private String userEmail;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void getUserTest() {
    UserDTO userDTO = restTemplate.getForObject(baseUrl + userEmail, UserDTO.class);
    assertEquals(userDTO.emailAddress, userEmail);
  }
}
