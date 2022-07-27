package com.epam.hw_6.util;

import com.epam.hw_6.model.entity.Order;
import com.epam.hw_6.model.entity.Pass;
import com.epam.hw_6.model.enums.PassStatus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static com.epam.hw_6.util.BookTestDataUtil.getBook;
import static com.epam.hw_6.util.OrderTestDataUtil.getOrder;

public class PassTestDataUtil {
  public static final long MOCK_PASS_ID = 1;
  public static final int MOCK_PENALTY = 10;

  public static Pass getPass() {
    Order order = getOrder();
    order.setOrderBooks(Collections.singletonList(getBook()));
    DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
    Pass pass = null;
    try {
      pass =
          Pass.builder()
              .id(1)
              .startDate(dateFormat.parse("2023-08-21"))
              .endDate(dateFormat.parse("2023-08-22"))
              .order(order)
              .passStatus(PassStatus.ACTIVE)
              .penalty(MOCK_PENALTY)
              .build();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return pass;
  }
}
