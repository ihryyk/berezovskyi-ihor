package com.sprinng.hw_3.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * Used to store information about Role.
 *
 * @author Ihor Berezovskyi
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private long id;

    private String name;
}
