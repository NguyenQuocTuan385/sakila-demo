package com.group06.sakila.services;

import com.group06.sakila.request_models.FilmRequest;
import com.group06.sakila.entities.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Film findById(Integer theId);

    Film createFilm(FilmRequest theFilm);

    Film updateFilm(FilmRequest theFilm, Integer theId);

    void deleteById(Integer theId);
}
