package com.sprinng.hw_3.controller.dto;


import com.sprinng.hw_3.model.enums.PassStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassDTO {

    public long id;

    public Date startDate;

    public Date endDate;

    public PassStatus passStatus;

    public int penalty;

    public OrderDTO order;


}
