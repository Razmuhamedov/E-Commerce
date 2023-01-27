package com.example.magazine.repository;

import com.example.magazine.entity.Order;
import com.example.magazine.type.PaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndDeletedAtIsNull(Long id);

    Page<Order> findAllByDeletedAtIsNull(Pageable pageable);
    Page<Order> findAllByPaymentTypeAndDeletedAtIsNull(PaymentType type, Pageable pageable);
}
