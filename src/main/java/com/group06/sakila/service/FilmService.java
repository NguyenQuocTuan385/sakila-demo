package com.group06.sakila.service;

import com.group06.sakila.requestmodel.FilmRequest;
import com.group06.sakila.entity.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    ResponseEntity<List<Film>> findAll();

    ResponseEntity<Film> findById(Integer theId);

    ResponseEntity<Film> createFilm(FilmRequest theFilm);

    ResponseEntity<Film> updateFilm(FilmRequest theFilm, Integer theId);

    ResponseEntity<String> deleteById(Integer theId);
}
