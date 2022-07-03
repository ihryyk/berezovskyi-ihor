package com.sprinng.hw_3.controller.dto;



import com.sprinng.hw_3.model.enums.BookOption;
import com.sprinng.hw_3.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * Used to store information about Order.
 *
 * @author Ihor Berezovskyi
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    /**
     * id of the Order.
     */
    public long id;
    /**
     * id of the Order.
     *
     * @see UserDTO
     */
    public UserDTO user;

    /**
     * books list of the Order.
     */
    public List<BookDTO> orderBooks;
    /**
     * books option of the Order.
     *
     * @see BookOption
     */
    public BookOption bookOption;
    /**
     * order status of the Order.
     *
     * @see BookOption
     */
    public OrderStatus orderStatus;
    /**
     * start day of the Order.
     *
     * @see OrderStatus
     */
    public Date arrivalDate;



}
