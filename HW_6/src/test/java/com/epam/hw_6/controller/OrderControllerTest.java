package com.epam.hw_6.controller;

import com.epam.hw_6.config.TestWebConfig;
import com.epam.hw_6.controller.dto.OrderDTO;
import com.epam.hw_6.controller.mapper.OrderMapper;
import com.epam.hw_6.model.enums.OrderStatus;
import com.epam.hw_6.service.OrderService;
import com.epam.hw_6.service.exeption.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

import static com.epam.hw_6.util.OrderTestDataUtil.MOCK_ORDER_ID;
import static com.epam.hw_6.util.OrderTestDataUtil.getOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = OrderController.class)
@AutoConfigureMockMvc
@Import(TestWebConfig.class)
class OrderControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private OrderService orderService;

  @Test
  void updateOrder() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    when(orderService.save(any())).thenReturn(orderDTO);
    mockMvc
        .perform(
            put("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andDo(print())
        .andExpect(status().isOk());
    verify(orderService).save(orderDTO);
  }

  @Test
  void updateOrderReturnsBadRequestForInvalidDate() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
    orderDTO.setArrivalDate(dateFormat.parse("2020-01-01"));
    when(orderService.save(any())).thenReturn(orderDTO);
    mockMvc
        .perform(
            put("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateBookWithServiceException() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    doThrow(ServiceException.class).when(orderService).save(orderDTO);
    mockMvc
        .perform(
            put("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getOrdersByUserId() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    long userId = 1;
    when(orderService.getAllByUserId(userId)).thenReturn(Collections.singletonList(orderDTO));
    mockMvc
        .perform(get("/order?userId=" + userId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].user").value(orderDTO.user));
  }

  @Test
  void getOrdersByUserIdWithServiceException() throws Exception {
    long userId = 1;
    doThrow(ServiceException.class).when(orderService).getAllByUserId(userId);
    mockMvc
        .perform(get("/order?userId=" + userId))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getOrderById() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    when(orderService.getById(MOCK_ORDER_ID)).thenReturn(orderDTO);
    mockMvc.perform(get("/order/" + MOCK_ORDER_ID)).andDo(print()).andExpect(status().isOk());
    verify(orderService).getById(MOCK_ORDER_ID);
  }

  @Test
  void getOrdersByIdWithServiceException() throws Exception {
    doThrow(ServiceException.class).when(orderService).getById(MOCK_ORDER_ID);
    mockMvc
        .perform(get("/order/" + MOCK_ORDER_ID))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void getAllOrders() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    when(orderService.getAll()).thenReturn(Collections.singletonList(orderDTO));
    mockMvc
        .perform(get("/order"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].user").value(orderDTO.user));
  }

  @Test
  void createOrder() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    when(orderService.save(any())).thenReturn(orderDTO);
    mockMvc
        .perform(
            post("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andDo(print())
        .andExpect(status().isCreated());
    verify(orderService).save(orderDTO);
  }

  @Test
  void createOrderWithServiceException() throws Exception {
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    doThrow(ServiceException.class).when(orderService).save(orderDTO);
    mockMvc
        .perform(
            post("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDTO)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  void changeOrderStatus() throws Exception {
    doNothing().when(orderService).changeOrderStatus(MOCK_ORDER_ID, OrderStatus.SUBMITTED);
    mockMvc
        .perform(
            patch("/order/" + MOCK_ORDER_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(OrderStatus.SUBMITTED)))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void changeOrderStatusWithServiceException() throws Exception {
    doThrow(ServiceException.class)
        .when(orderService)
        .changeOrderStatus(MOCK_ORDER_ID, OrderStatus.SUBMITTED);
    mockMvc
        .perform(
            patch("/order/" + MOCK_ORDER_ID)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(OrderStatus.SUBMITTED)))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
