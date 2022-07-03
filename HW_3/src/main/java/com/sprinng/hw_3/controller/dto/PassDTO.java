package com.sprinng.hw_3.controller.dto;


import com.sprinng.hw_3.model.enums.PassStatus;
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
public class PassDTO {
    /**
     * id of the Pass.
     */
    public long id;
    /**
     * start date of the Pass.
     */
    public Date startDate;
    /**
     * end date of the Pass.
     */
    public Date endDate;
    /**
     * pass status of the Pass.
     */
    public PassStatus passStatus;
    /**
     * penalty of the Pass.
     */
    public int penalty;

    /**
     * order of the Pass.
     * @see OrderDTO
     */
    public OrderDTO order;


}
