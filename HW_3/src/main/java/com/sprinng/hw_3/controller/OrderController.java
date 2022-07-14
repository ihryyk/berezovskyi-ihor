package com.sprinng.hw_3.controller;

import com.sprinng.hw_3.controller.dto.OrderDTO;
import com.sprinng.hw_3.model.enums.OrderStatus;
import com.sprinng.hw_3.service.OrderService;
import com.sprinng.hw_3.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;

  @ResponseStatus(HttpStatus.OK)
  @PutMapping
  public void updateOrder(@RequestBody OrderDTO orderDTO) throws ServiceException {
    log.info("update order");
    orderService.update(orderDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(params = "userId")
  public List<OrderDTO> getOrdersByUserId(@RequestParam(name = "userId") long userId)
      throws ServiceException {
    log.info("get all orders by user id {}", userId);
    return orderService.getAllByUserId(userId);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "{id}")
  public OrderDTO getOrdersById(@PathVariable long id) throws ServiceException {
    log.info("get order by id {}", id);
    return orderService.getById(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping
  public List<OrderDTO> getAllOrders() throws ServiceException {
    log.info("get all orders ");
    return orderService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createBook(@RequestBody OrderDTO orderDTO) throws ServiceException {
    log.info("create new order");
    orderService.create(orderDTO);
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(value = "{id}")
  public void changeOrderStatus(@PathVariable long id, @RequestBody OrderStatus orderStatus) {
    log.info("change order status to {} in order with id {}", orderStatus, id);
    orderService.changeOrderStatus(id, orderStatus);
  }
}
