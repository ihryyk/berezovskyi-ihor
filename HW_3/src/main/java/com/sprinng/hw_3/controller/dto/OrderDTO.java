package com.sprinng.hw_3.controller.dto;


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
public class OrderDTO {

    public long id;

    public UserDTO user;


    public List<BookDTO> orderBooks;

    public BookOption bookOption;

    public OrderStatus orderStatus;

    public Date arrivalDate;


}
