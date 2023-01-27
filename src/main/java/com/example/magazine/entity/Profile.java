package com.example.magazine.entity;

import com.example.magazine.type.Role;
import com.example.magazine.type.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter

@Entity
@Table(name = ("profiles"))
public class Profile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @ManyToOne
    @JoinColumn(name = ("image_id"), insertable = false, updatable = false)
    private Image image;
    @Column(name = ("image_id"))
    private Long imageId;

    @Column(name = ("verified_at"))
    private LocalDateTime verifiedAt;
    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
