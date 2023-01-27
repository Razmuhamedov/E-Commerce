package com.example.magazine.service;

import com.example.magazine.dto.order.OrderCreateDto;
import com.example.magazine.dto.order.OrderDto;
import com.example.magazine.entity.Order;
import com.example.magazine.exception.BadRequest;
import com.example.magazine.repository.OrderRepo;
import com.example.magazine.type.OrderStatus;
import com.example.magazine.type.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepo orderRepo, OrderItemService orderItemService) {
        this.orderRepo = orderRepo;
        this.orderItemService = orderItemService;
    }

    public String createOrder(OrderCreateDto dto) {
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        order.setDeliveryDate(LocalDate.now().plusDays(3));
        convertToEntity(dto, order);
        orderRepo.save(order);
        return "Order saved successful";
    }

    public String updateOrder(Long id, OrderCreateDto dto) {
        Order order = getEntity(id);
        order.setUpdatedAt(LocalDateTime.now());
        convertToEntity(dto, order);
        orderRepo.save(order);
        return "Order updated successful";
    }

    public String softDeleteOrder(Long id) {
        Order order = getEntity(id);
        if(!order.getStatus().equals(OrderStatus.CREATED)){
            return "You can not delete this order!";
        }
        order.setDeletedAt(LocalDateTime.now());
        orderRepo.save(order);
        return "Order deleted!";
    }

    public OrderDto getOrder(Long id) {
        return convertToDto(getEntity(id), new OrderDto());
    }

    public List<OrderDto> getAll(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Order> pages = orderRepo.findAllByDeletedAtIsNull(request);
        return pages.stream().map(order -> convertToDto(order, new OrderDto())).collect(Collectors.toList());
    }


    public List<OrderDto> getAllByPaymentType(String type, int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Order> pages = orderRepo.findAllByPaymentTypeAndDeletedAtIsNull(PaymentType.valueOf(type), request);
        return pages.stream().map(order -> convertToDto(order, new OrderDto())).collect(Collectors.toList());
    }

    public Long countByProfileId(Long id) {
        return null;
    }

    private OrderDto convertToDto(Order order, OrderDto dto){
        dto.setId(order.getId());
        dto.setAddress(order.getAddress());
        dto.setContact(order.getContact());
        dto.setProfile(order.getProfile());
        dto.setStatus(order.getStatus());
        dto.setDeliveredAt(order.getDeliveredAt());
        dto.setDeliveryDate(order.getDeliveryDate());
        dto.setPaymentType(order.getPaymentType());
        dto.setRequirement(order.getRequirement());
        return dto;
    }

    private void convertToEntity(OrderCreateDto dto, Order order){
        order.setRequirement(dto.getRequirement());
        order.setContact(dto.getContact());
        order.setAddress(dto.getAddress());
        order.setProfileId(dto.getProfileId());
        order.setPaymentType(PaymentType.valueOf(dto.getPaymentType()));
        order.setDeliveryDate(dto.getDeliveryDate());
    }

    private Order getEntity(Long id){
        Optional<Order> optional = orderRepo.findByIdAndDeletedAtIsNull(id);
        if(optional.isEmpty()){
            throw new BadRequest("Order not found!");
        }
        return optional.get();
    }


    public String deleteOrder(Long id) {
        Order order = getEntity(id);
        orderRepo.delete(order);
        return "Order deleted from database!";
    }
}
