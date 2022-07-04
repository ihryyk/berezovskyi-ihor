package com.epam.hw_4.controller.dto;


import com.epam.hw_4.model.enums.PassStatus;
import com.epam.hw_4.model.enums.PassStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassDTO {

    public long id;

    @Future
    public Date startDate;

    @Future
    public Date endDate;

    @NotNull
    public PassStatus passStatus;

    @Positive
    public int penalty;

    @NotNull
    public OrderDTO order;


}
