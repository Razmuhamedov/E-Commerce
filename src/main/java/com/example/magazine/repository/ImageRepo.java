package com.example.magazine.repository;

import com.example.magazine.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findByToken(String token);
}
