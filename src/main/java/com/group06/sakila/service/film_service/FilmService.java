package com.group06.sakila.service.film_service;

import com.group06.sakila.entity.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    ResponseEntity<List<Film>> findAll();

    ResponseEntity<Film> findById(Integer theId);

    ResponseEntity<Film> createFilm(Film theFilm);

    ResponseEntity<Film> updateFilm(Film theFilm, Integer theId);

    ResponseEntity<String> deleteById(Integer theId);
}
