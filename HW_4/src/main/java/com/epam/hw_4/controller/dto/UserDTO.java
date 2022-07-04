package com.epam.hw_4.controller.dto;


import com.epam.hw_4.controller.Validation.UserNameValidation;
import com.epam.hw_4.model.entity.Role;
import com.epam.hw_4.model.enums.LockStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public long id;

    @UserNameValidation
    public String name;

    @NotBlank
    @Email
    public String emailAddress;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    public String password;

    @NotNull
    public Role role;

    @NotNull
    public LockStatus lockStatus;


}
