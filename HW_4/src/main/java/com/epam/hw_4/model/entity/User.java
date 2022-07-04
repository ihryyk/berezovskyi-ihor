package com.epam.hw_4.model.entity;


import com.epam.hw_4.model.enums.LockStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used to store information about User.
 *
 * @author Ihor Berezovskyi
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    private String name;

    private String emailAddress;

    private String password;

    private Role role;

    private LockStatus lockStatus;


}
