package com.alfredTech.e_rental_application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message= "password is required")
    private String password;
}
