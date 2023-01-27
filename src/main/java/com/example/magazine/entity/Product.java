package com.example.magazine.entity;

import com.example.magazine.type.ProductStatus;
import com.example.magazine.type.ProductType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("products"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double rate;
    private Boolean visible;

    @Enumerated(EnumType.STRING)
    @Column(name = ("product_type"))
    private ProductType productType;
    @Enumerated(EnumType.STRING)
    @Column(name = ("product_status"))
    private ProductStatus status;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
