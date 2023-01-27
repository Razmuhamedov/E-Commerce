package com.example.magazine.dto.order;

import com.example.magazine.entity.Order;
import com.example.magazine.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private Long id;
    private Integer amount;
    private Double price;
    private Order order;
    private Product product;
}
