package com.group06.sakila.repositories;

import com.group06.sakila.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    boolean existsByUsername(String username);
    Optional<Staff> findByUsername(String username);
    Optional<Staff> findByRefreshToken(@Param("refreshToken") String refreshToken);
}
