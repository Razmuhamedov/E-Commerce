package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("rate"))
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rate;

    @ManyToOne
    @JoinColumn(name = ("profile_id"), insertable = false, updatable = false)
    private Profile profile;
    @Column(name = ("profile_id"))
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = ("product_id"), insertable = false, updatable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Long productId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
}
