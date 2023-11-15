package com.group06.sakila.services;

import com.group06.sakila.entities.Film;
import com.group06.sakila.request_models.FilmRequest;

import java.util.List;

public interface ServerBService {
    List<Film> getAllFilms();
    Film getFilmById(Integer id);

    Film createFilm(FilmRequest filmRequest);

}
