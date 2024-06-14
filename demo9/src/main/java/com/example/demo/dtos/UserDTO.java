package com.example.demo.dtos;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Fullname is required")
    private String fullname;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String address;

    @NotBlank(message = "Password is required")
    private String password;

    private boolean active;

    private Date dateOfBirth;

    private int facebookAccountId;

    private int googleAccountId;

    @NotNull(message = "Role is required")
    private Role role;
}
