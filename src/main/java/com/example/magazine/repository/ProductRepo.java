package com.example.magazine.repository;

import com.example.magazine.entity.Product;
import com.example.magazine.type.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepo  extends JpaRepository<Product, Long> {
    Long countByProductTypeAndDeletedAtIsNull(ProductType type);

    @Query(value = "select p from Product p where p.deletedAt is null")
    Page<Product> getAllByDeletedAt(Pageable pageable);

    Optional<Product> findByIdAndVisibleIsTrue(Long id);
}
