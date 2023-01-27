package com.example.magazine.repository;

import com.example.magazine.entity.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Optional<Profile> findByEmailAndDeletedAtIsNull(String email);
    Optional<Profile> findByEmailOrPhoneAndDeletedAtIsNull(String email, String phone);

    @Query(value = "SELECT p from Profile p where p.deletedAt is null")
    Page<Profile> getAllByDeletedAt(Pageable pageable);

    Optional<Profile> findByIdAndDeletedAtIsNull(Long id);
    Long countByDeletedAt = null;
}
