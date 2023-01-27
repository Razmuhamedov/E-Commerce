package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = ("product_images"))
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ("product_id"), insertable = false, updatable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Long productId;

    @ManyToOne
    @JoinColumn(name = ("image_id"), insertable = false, updatable = false)
    private Image image;
    @Column(name = ("image_id"))
    private Long imageId;
}
