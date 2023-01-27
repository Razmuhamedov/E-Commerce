package com.example.magazine.dto.order;

import com.example.magazine.entity.Order;
import com.example.magazine.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderItemCreateDto {
    @NotNull(message = "Amount not should be null!")
    private Integer amount;
    @NotNull(message = "Price not should be null!")
    private Double price;
    @NotNull(message = "Order not should be null")
    private Long orderId;
    @NotNull(message = "Product not should be null")
    private Long productId;
}
