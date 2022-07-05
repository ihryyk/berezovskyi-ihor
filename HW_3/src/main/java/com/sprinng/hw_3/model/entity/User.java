package com.sprinng.hw_3.model.entity;


import com.sprinng.hw_3.model.enums.LockStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
