package com.sprinng.hw_3.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

    public long id;

    public int year;

    public String title;

    public String author;

    public String publishingHouse;

    public int number;

}
