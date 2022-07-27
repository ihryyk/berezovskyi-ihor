package com.epam.hw_5.controller.dto;

import com.epam.hw_5.model.enums.BookOption;
import com.epam.hw_5.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Future;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

  public long id;

  @NotNull public UserDTO user;

  @NotNull public List<BookDTO> orderBooks;

  @NotNull public BookOption bookOption;

  @NotNull public OrderStatus orderStatus;

  @NotNull @Future public Date arrivalDate;
}
