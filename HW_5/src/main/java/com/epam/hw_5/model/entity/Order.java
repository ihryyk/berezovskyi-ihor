package com.epam.hw_5.model.entity;

import com.epam.hw_5.model.enums.BookOption;
import com.epam.hw_5.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order1")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private long id;

  @JsonBackReference
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_user")
  private User user;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "order_book",
      joinColumns = @JoinColumn(name = "id_order"),
      inverseJoinColumns = @JoinColumn(name = "id_book"))
  private List<Book> orderBooks;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_type")
  private BookOption bookOption;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_status")
  private OrderStatus orderStatus;

  @Column(name = "arrival_date")
  private Date arrivalDate;
}
