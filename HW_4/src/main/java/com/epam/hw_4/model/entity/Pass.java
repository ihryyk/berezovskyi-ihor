package com.epam.hw_4.model.entity;


import com.epam.hw_4.model.enums.PassStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Used to store information about Pass.
 *
 * @author Ihor Berezovskyi
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pass {

    private long id;

    private Date startDate;

    private Date endDate;

    private PassStatus passStatus;

    private int penalty;


    private Order order;


}
