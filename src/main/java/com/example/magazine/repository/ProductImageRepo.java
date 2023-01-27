package com.example.magazine.repository;

import com.example.magazine.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findAllByProductId(Long productId);

    void deleteAllByProductId(Long productId);
}
