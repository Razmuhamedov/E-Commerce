package com.example.magazine.controller;

import com.example.magazine.configuration.SecurityUtil;
import com.example.magazine.dto.order.OrderCreateDto;
import com.example.magazine.dto.order.OrderDto;
import com.example.magazine.dto.order.OrderItemDto;
import com.example.magazine.service.OrderItemService;
import com.example.magazine.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;


    public OrderController(OrderService orderService, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.orderItemService = orderItemService;
    }

////////////////////////////////*ADMIN, MODERATOR*///////////////////////////////////////////////////


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
        String result = orderService.deleteOrder(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id){
        OrderDto result = orderService.getOrder(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam("page") int page,
                                    @RequestParam("size") int size){
        List<OrderDto> result = orderService.getAll(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllItem")
    public ResponseEntity<?> getAllItem(@RequestParam("page") int page,
                                    @RequestParam("size") int size){
        List<OrderDto> result = orderItemService.getAllItems(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByProfile/{id}")
    public ResponseEntity<?> getByProfile(@PathVariable("id") Long id,
                                        @RequestParam("page") int page,
                                        @RequestParam("size") int size){
        List<OrderDto> result = orderItemService.getAllItemsByProfile(id, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByPayment{type}")
    public ResponseEntity<?> getByPaymentType(@PathVariable("type") String type,
                                              @RequestParam("page") int page,
                                              @RequestParam("size") int size){
        List<OrderDto> result = orderService.getAllByPaymentType(type, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/countByProfile/{id}")
    public ResponseEntity<?> countByProfile(@PathVariable("id") Long id){
        Long result = orderService.countByProfileId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByProduct/{id}")
    public ResponseEntity<?> getByProduct(@PathVariable("id") Long id,
                                        @RequestParam("page") int page,
                                        @RequestParam("size") int size){
        List<OrderDto> result = orderItemService.getAllItemsByProduct(id, page, size);
        return ResponseEntity.ok(result);
    }





//////////////////////////*USER*////////////////////////////////////////////////////

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderCreateDto dto){
    String result = orderService.createOrder(dto);
    return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id,
                                         @RequestBody @Valid OrderCreateDto dto){
        String result = orderService.updateOrder(id, dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getOrderItem(@PathVariable("id") Long id){
        OrderItemDto result = orderItemService.getOrderItem(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getMyItems")
    public ResponseEntity<?> getMyItems(@RequestParam("page") int page,
                                        @RequestParam("size") int size){
        List<OrderDto> result = orderItemService.getAllItemsByProfile(SecurityUtil.getProfileId(), page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> softDeleteOrder(@PathVariable("id") Long id){
        String result = orderService.softDeleteOrder(id);
        return ResponseEntity.ok(result);
    }



}
