package com.epam.hw_4.controller.dto;



import com.epam.hw_4.model.enums.BookOption;
import com.epam.hw_4.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
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

    public long id;

    @NotBlank
    public UserDTO user;

    @NotNull
    public List<BookDTO> orderBooks;

    @NotNull
    public BookOption bookOption;

    @NotNull
    public OrderStatus orderStatus;

    @Future
    public Date arrivalDate;



}
