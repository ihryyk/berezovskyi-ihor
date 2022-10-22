package com.epam.hw_5.controller;

import com.epam.hw_5.controller.dto.OrderDTO;
import com.epam.hw_5.model.enums.OrderStatus;
import com.epam.hw_5.service.OrderService;
import com.epam.hw_5.service.exeption.ControllerException;
import com.epam.hw_5.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updateOrder(@RequestBody @Valid OrderDTO orderDTO) {
    log.info("update order");
    orderService.save(orderDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "userId")
  public List<OrderDTO> getOrdersByUserId(@RequestParam(name = "userId") long userId) {
    try {
      log.info("get all orders by user id {}", userId);
      return orderService.getAllByUserId(userId);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}")
  public OrderDTO getOrderById(@PathVariable long id) {
    try {
      log.info("get order by id {}", id);
      return orderService.getById(id);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<OrderDTO> getAllOrders() {
    log.info("get all orders ");
    return orderService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createOrder(@RequestBody @Valid OrderDTO orderDTO) {
    try {
      log.info("create new order");
      orderService.save(orderDTO);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changeOrderStatus(@PathVariable long id, @RequestBody OrderStatus orderStatus) {
    try {
      log.info("change order status to {} in order with id {}", orderStatus, id);
      orderService.changeOrderStatus(id, orderStatus);
    } catch (ServiceException ex) {
      throw new ControllerException(ex.getMessage());
    }
  }
}
