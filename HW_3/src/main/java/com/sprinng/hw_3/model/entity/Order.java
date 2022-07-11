package com.sprinng.hw_3.model.entity;

import com.sprinng.hw_3.model.enums.BookOption;
import com.sprinng.hw_3.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private long id;

  private User user;

  private List<Book> orderBooks;

  private BookOption bookOption;

  private OrderStatus orderStatus;

  private Date arrivalDate;
}
