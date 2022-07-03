package com.sprinng.hw_3.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used to store information about Book.
 *
 * @author Ihor Berezovskyi
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {
    //    /**
//     * variable to define deleted books
//     */
//    boolean deleted;
//    /**
//     * id of the Book.
//     */
    public long id;
    /**
     * year of publication of the Book.
     */
    public int year;
    /**
     * title of the Book.
     */
    public String title;
    /**
     * author of the Book.
     */
    public String author;
    /**
     * publishing house of the Book.
     */
    public String publishingHouse;
    /**
     * number of copies of the Book.
     */
    public int number;

}
