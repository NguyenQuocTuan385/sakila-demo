package com.group06.sakila.controller;

import com.group06.sakila.requestmodel.FilmRequest;
import com.group06.sakila.entity.Film;
import com.group06.sakila.service.FilmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/films")
public class FilmController {
    private FilmService filmService;

    @GetMapping("")
    ResponseEntity<List<Film>> getAllFilms() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Film> findById(@PathVariable Integer id) {
        return filmService.findById(id);
    }

    //Create film
    @PostMapping("")
    ResponseEntity<Film> createFilm(@RequestBody @Valid FilmRequest filmRequest) {
        return filmService.createFilm(filmRequest);
    }

    @PutMapping("/{id}")
    ResponseEntity<Film> updateActor(@RequestBody @Valid FilmRequest filmRequest, @PathVariable Integer id) {
        return filmService.updateFilm(filmRequest, id);
    }

    // Delete film
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteFilm(@PathVariable Integer id) {
        return filmService.deleteById(id);
    }
}
