package com.epam.hw_5.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

  public long id;

  @NotBlank @Positive public int year;

  @NotNull @NotBlank public String title;

  @NotNull @NotBlank public String author;

  @NotNull @NotBlank public String publishingHouse;

  @NotBlank public int number;

  @NotBlank public boolean deleted;
}
