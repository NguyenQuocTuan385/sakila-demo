package com.group06.sakila.repositories;

import com.group06.sakila.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Integer> {
    Optional<Film> findByTitle(String title);
}
