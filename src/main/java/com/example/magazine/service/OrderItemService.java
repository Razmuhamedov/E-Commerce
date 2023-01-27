package com.example.magazine.service;

import com.example.magazine.dto.order.OrderDto;
import com.example.magazine.dto.order.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    public List<OrderDto> getAllItems(int page, int size) {
        return null;
    }

    public List<OrderDto> getAllItemsByProfile(Long id, int page, int size) {
        return null;
    }

    public List<OrderDto> getAllItemsByProduct(Long id, int page, int size) {
        return null;
    }

    public OrderItemDto getOrderItem(Long id) {
        return null;
    }
}
