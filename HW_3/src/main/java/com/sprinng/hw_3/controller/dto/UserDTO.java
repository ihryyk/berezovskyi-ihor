package com.sprinng.hw_3.controller.dto;


import com.sprinng.hw_3.model.entity.Role;
import com.sprinng.hw_3.model.enums.LockStatus;
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
public class UserDTO {

    public long id;

    public String name;

    public String emailAddress;

    public String password;

    public Role role;

    public LockStatus lockStatus;


}
