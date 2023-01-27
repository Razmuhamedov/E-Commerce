package com.example.magazine.entity;

import com.example.magazine.type.OrderStatus;
import com.example.magazine.type.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("orders"))
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requirement;
    private String contact;
    private String address;

    @ManyToOne
    @JoinColumn(name = ("profile_id"), updatable = false, insertable = false)
    private Profile profile;
    @Column(name = ("profile_id"))
    private Long profileId;

    @Enumerated(EnumType.STRING)
    @Column(name = ("payment_type"))
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = ("order_status"))
    private OrderStatus status;

    @Column(name = ("delivery_date"))
    private LocalDate deliveryDate;
    @Column(name = ("delivered_at"))
    private LocalDateTime deliveredAt;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;


}
