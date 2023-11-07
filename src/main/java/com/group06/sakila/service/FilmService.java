package com.group06.sakila.service;

import com.group06.sakila.request_model.FilmRequest;
import com.group06.sakila.entity.Film;

import java.util.List;

public interface FilmService {
    List<Film> findAll();

    Film findById(Integer theId);

    Film createFilm(FilmRequest theFilm);

    Film updateFilm(FilmRequest theFilm, Integer theId);

    void deleteById(Integer theId);
}
