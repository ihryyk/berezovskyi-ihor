package com.epam.hw_6.util;

import com.epam.hw_6.model.entity.Book;
import com.epam.hw_6.model.entity.Order;
import com.epam.hw_6.model.entity.User;
import com.epam.hw_6.model.enums.BookOption;
import com.epam.hw_6.model.enums.OrderStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderTestDataUtil {

  public static final long MOCK_ORDER_ID = 1;

  public static Order getOrder() {
    Book book = BookTestDataUtil.getBook();
    User user = UserTestDataUtil.getUser();
    DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
    Order order = null;
    try {
      order =
          Order.builder()
              .id(1)
              .orderStatus(OrderStatus.IN_PROCESSING)
              .bookOption(BookOption.READING_ROOM)
              .orderBooks(List.of(book))
              .user(user)
              .arrivalDate(dateFormat.parse("2023-01-01"))
              .build();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return order;
  }
}
