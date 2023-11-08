package com.group06.sakila.repositories;

import com.group06.sakila.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    boolean existsByUsername(String username);
    Optional<Staff> findByUsername(String username);
}
