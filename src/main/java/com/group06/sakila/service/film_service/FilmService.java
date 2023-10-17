package com.group06.sakila.service.film_service;

import com.group06.sakila.dto.response.SuccessResponse;
import com.group06.sakila.entity.Film;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FilmService {
    ResponseEntity<SuccessResponse> findAll();

    ResponseEntity<SuccessResponse> findById(Integer theId);

    ResponseEntity<SuccessResponse> createFilm(Film theFilm);

    ResponseEntity<SuccessResponse> updateFilm(Film theFilm, Integer theId);

    ResponseEntity<SuccessResponse> deleteById(Integer theId);
}
