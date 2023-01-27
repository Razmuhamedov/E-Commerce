package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("wishlist"))
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ("product_id"), insertable = false, updatable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Long productId;

    @ManyToOne
    @JoinColumn(name = ("profile_id"), insertable = false, updatable = false)
    private Profile profile;
    @Column(name = ("profile_id"))
    private Long profileId;

    private Integer quantity;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;

}
