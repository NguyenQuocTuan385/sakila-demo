package com.group06.sakila.services;

import com.group06.sakila.entities.Film;

import java.util.List;

public interface ServerBService {
    List<Film> getAllFilms();
    Film getFilmById(Integer id);
}
