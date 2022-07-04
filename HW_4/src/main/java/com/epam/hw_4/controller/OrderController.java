package com.epam.hw_4.controller;


import com.epam.hw_4.controller.dto.OrderDTO;
import com.epam.hw_4.model.enums.OrderStatus;
import com.epam.hw_4.service.OrderService;
import com.epam.hw_4.service.exeption.ControllerException;
import com.epam.hw_4.service.exeption.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/order")
    public void updateOrder(@RequestBody OrderDTO orderDTO) {
        try {
            log.info("update order");
            orderService.update(orderDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/order/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable long userId) throws ServiceException {
        try {
            log.info("get all orders by user id {}", userId);
            return orderService.getAllByUserId(userId);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/order/{id}")
    public OrderDTO getOrdersById(@PathVariable long id) throws ServiceException {
        try {
            log.info("get order by id {}", id);
            return orderService.getById(id);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/order/")
    public List<OrderDTO> getAllOrders() throws ServiceException {
        try {
            log.info("get all orders ");
            return orderService.getAll();
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/order")
    public void createBook(@RequestBody OrderDTO orderDTO) throws ServiceException {
        try {
            log.info("create new order");
            orderService.create(orderDTO);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/order/{id}")
    public void changeOrderStatus(@PathVariable long id, @RequestBody OrderStatus orderStatus) {
        try {
            log.info("change order status to {} in order with id {}", orderStatus, id);
            orderService.changeOrderStatus(id, orderStatus);
        } catch (ServiceException ex) {
            throw new ControllerException(ex.getMessage());
        }
    }
}
