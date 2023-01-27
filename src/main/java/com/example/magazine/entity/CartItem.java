package com.example.magazine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("cart_items"))
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = ("product_id"), insertable = false, updatable = false)
    private Product product;
    @Column(name = ("product_id"))
    private Long productId;

    @ManyToOne
    @JoinColumn(name = ("cart_id"), insertable = false, updatable = false)
    private Cart cart;
    @Column(name = ("cart_id"))
    private Long cartId;



    @ManyToOne
    @JoinColumn(name = ("order_id"), insertable = false, updatable = false)
    private Order order;
    @Column(name = ("order_id"))
    private Long orderId;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
