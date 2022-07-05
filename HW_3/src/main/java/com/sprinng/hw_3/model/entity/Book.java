package com.sprinng.hw_3.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private long id;

    private int year;

    private String title;

    private String author;

    private String publishingHouse;

    private int number;

}
