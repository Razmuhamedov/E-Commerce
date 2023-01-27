package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("product_categories"))
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = ("product_id"), updatable = false, insertable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Long productId;

    @ManyToOne
    @JoinColumn(name = ("category_id"), updatable = false, insertable = false)
    private Category category;
    @Column(name = ("category_id"))
    private Long categoryId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
