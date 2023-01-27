package com.example.magazine.dto.profile;

import com.example.magazine.entity.Image;
import com.example.magazine.type.Role;
import com.example.magazine.type.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String address;
    private Role role;
    private ProfileStatus status;
    private Image image;

}
