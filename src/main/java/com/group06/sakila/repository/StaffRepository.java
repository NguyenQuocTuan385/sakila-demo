package com.group06.sakila.repository;

import com.group06.sakila.entity.Film;
import com.group06.sakila.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    boolean existsByEmail(String username);
    Optional<Staff> findByEmail(String username);
}
