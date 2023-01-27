package com.example.magazine.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
    @NotBlank(message = "Email not should be empty or null!")
    @Email(message = "Email error!")
    private String email;
    @Length(message = "Password should be larger than 8 characters!")
    private String password;
}
