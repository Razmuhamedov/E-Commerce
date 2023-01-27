package com.example.magazine.dto.profile;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class ProfileCreateDto {
    @Length(min = 3, message = "Name should be larger than 3 characters!")
    private String name;
    @Length(min = 3, message = "Surname should be larger than 3 characters!")
    private String surname;
    @Email(message = "Input only email format!")
    private String email;
    @NotBlank(message = "Email not should be null or empty!")
    @Positive(message = "Please, input only number format!")
    private String phone;
    @Length(min = 8, message = "Password should be larger than 8 characters!")
    private String password;
    @NotBlank(message = "Address not should be null or empty!")
    private String address;
    private Long imageId;
}
