package com.epam.hw_6.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

  public long id;

  @NotNull @Positive public int year;

  @NotNull @NotBlank public String title;

  @NotNull @NotBlank public String author;

  @NotNull @NotBlank public String publishingHouse;

  @Positive public int number;

  @NotNull public boolean deleted;
}
