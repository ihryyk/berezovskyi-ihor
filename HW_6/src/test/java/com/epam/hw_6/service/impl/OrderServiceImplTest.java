package com.epam.hw_6.service.impl;

import com.epam.hw_6.controller.dto.OrderDTO;
import com.epam.hw_6.controller.mapper.OrderMapper;
import com.epam.hw_6.model.entity.Order;
import com.epam.hw_6.model.enums.OrderStatus;
import com.epam.hw_6.repository.OrderRepository;
import com.epam.hw_6.repository.UserRepository;
import com.epam.hw_6.service.exeption.RepositoryException;
import com.epam.hw_6.service.exeption.ServiceException;
import com.epam.hw_6.util.UserTestDataUtil;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.epam.hw_6.util.OrderTestDataUtil.MOCK_ORDER_ID;
import static com.epam.hw_6.util.OrderTestDataUtil.getOrder;
import static com.epam.hw_6.util.UserTestDataUtil.MOCK_USER_ID;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

  @InjectMocks private OrderServiceImpl orderService;
  @Mock private OrderRepository orderRepository;
  @Mock private UserRepository userRepository;

  @Test
  void save() {
    Order order = getOrder();
    OrderDTO orderDTO = OrderMapper.INSTANCE.mapToDto(getOrder());
    when(orderRepository.save(any())).thenReturn(order);
    OrderDTO savedOrder = orderService.save(orderDTO);
    assertThat(
        savedOrder,
        allOf(
            hasProperty("orderStatus", CoreMatchers.equalTo(order.getOrderStatus())),
            hasProperty("bookOption", CoreMatchers.equalTo(order.getBookOption()))));
  }

  @Test
  void getAllByUserId() {
    when(orderRepository.getAllByUserId(MOCK_USER_ID))
        .thenReturn(Collections.singletonList(getOrder()));
    when(userRepository.findById(MOCK_USER_ID)).thenReturn(Optional.of(UserTestDataUtil.getUser()));
    List<OrderDTO> actualOrders = orderService.getAllByUserId(MOCK_USER_ID);
    assertThat(actualOrders, hasSize(1));
  }

  @Test
  void getAllByUserIdWithRepositoryException() {
    doThrow(RepositoryException.class).when(userRepository).findById(MOCK_USER_ID);
    assertThrows(ServiceException.class, () -> orderService.getAllByUserId(MOCK_USER_ID));
  }

  @Test
  void getById() {
    Order expectedOrder = getOrder();
    when(orderRepository.findById(MOCK_ORDER_ID)).thenReturn(Optional.of(expectedOrder));
    OrderDTO actualOrder = orderService.getById(MOCK_ORDER_ID);
    assertThat(actualOrder.orderStatus, equalTo(OrderStatus.IN_PROCESSING));
  }

  @Test
  void getByIdWithRepositoryException() {
    doThrow(RepositoryException.class).when(orderRepository).findById(MOCK_ORDER_ID);
    assertThrows(ServiceException.class, () -> orderService.getById(MOCK_ORDER_ID));
  }

  @Test
  void getAll() {
    when(orderRepository.findAll()).thenReturn(Collections.singletonList(getOrder()));
    List<OrderDTO> actualOrders = orderService.getAll();
    assertThat(actualOrders, hasSize(1));
  }

  @Test
  void changeOrderStatus() {
    Order order = getOrder();
    when(orderRepository.findById(MOCK_ORDER_ID)).thenReturn(Optional.of(order));
    when(orderRepository.save(any())).thenReturn(order);
    orderService.changeOrderStatus(MOCK_ORDER_ID, OrderStatus.IN_PROCESSING);
    verify(orderRepository, times(1)).save(order);
  }

  @Test
  void changeOrderStatusWithRepositoryException() {
    doThrow(RepositoryException.class).when(orderRepository).findById(MOCK_ORDER_ID);
    assertThrows(
        ServiceException.class,
        () -> orderService.changeOrderStatus(MOCK_ORDER_ID, OrderStatus.IN_PROCESSING));
  }
}
