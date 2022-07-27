package com.epam.hw_6.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

  @Column(name = "deleted")
  boolean deleted;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "book_id")
  private long id;

  @Column(name = "year")
  private int year;

  @Column(name = "title")
  private String title;

  @Column(name = "author")
  private String author;

  @Column(name = "publishing_house")
  private String publishingHouse;

  @Column(name = "number")
  private int number;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "order_book",
      joinColumns = @JoinColumn(name = "id_book"),
      inverseJoinColumns = @JoinColumn(name = "id_order"))
  private List<Order> orders = new ArrayList<>();
}
