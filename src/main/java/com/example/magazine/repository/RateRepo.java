package com.example.magazine.repository;

import com.example.magazine.entity.Rate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RateRepo extends JpaRepository<Rate, Long> {
    Optional<Rate> findByProfileIdAndProductId(Long profileId, Long productId);

    @Query(value = "select avg(rate) from Rate where productId =: id")
    Double getProductRate(@Param("id") Long id);

    Page<Rate> findAllByProfileId(Long profileId, Pageable pageable);
    Page<Rate> findAllByProductId(Long productId, Pageable pageable);
}
