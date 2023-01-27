package com.example.magazine.dto.order;

import com.example.magazine.entity.Profile;
import com.example.magazine.type.OrderStatus;
import com.example.magazine.type.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private String requirement;
    private String contact;
    private String address;
    private Profile profile;
    private PaymentType paymentType;
    private OrderStatus status;
    private LocalDate deliveryDate;
    private LocalDateTime deliveredAt;
}
