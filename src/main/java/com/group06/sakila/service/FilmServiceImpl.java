package com.group06.sakila.service;

import com.group06.sakila.requestmodel.FilmRequest;
import com.group06.sakila.exception.NotFoundException;
import com.group06.sakila.entity.Film;
import com.group06.sakila.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    private FilmRepository filmRepository;

    @Override
    public ResponseEntity<List<Film>> findAll() {
        List<Film> foundFilms = filmRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(foundFilms);
    }

    @Override
    public ResponseEntity<Film> findById(Integer theId) { // Optional mean the result can be null
        Film foundFilm = filmRepository.findById(theId).orElse(null);
        if (foundFilm != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundFilm);
        }
        throw new NotFoundException("Cannot find film with id = " + theId);
    }

    @Override
    public ResponseEntity<Film> createFilm(FilmRequest filmRequest) {
        Film filmSaved = new Film();
        filmSaved.setTitle(filmRequest.getTitle());
        filmSaved.setDescription(filmRequest.getDescription());
        filmSaved.setReleaseYear(filmRequest.getReleaseYear());
        filmSaved.setLanguageId(filmRequest.getLanguageId());
        filmSaved.setOriginalLanguageId(filmRequest.getOriginalLanguageId());
        filmSaved.setRentalDuration(filmRequest.getRentalDuration());
        filmSaved.setRentalRate(filmRequest.getRentalRate());
        filmSaved.setLength(filmRequest.getLength());
        filmSaved.setReplacementCost(filmRequest.getReplacementCost());
        filmSaved.setRating(filmRequest.getRating());
        filmSaved.setSpecialFeatures(filmRequest.getSpecialFeatures());
        filmSaved.setLastUpdate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(filmSaved));
    }

    @Override
    public ResponseEntity<Film> updateFilm(FilmRequest filmRequest, Integer theId) {
        Film tempFilm = filmRepository.findById(theId).orElse(null);

        if (tempFilm != null) {
            tempFilm.setTitle(filmRequest.getTitle());
            tempFilm.setDescription(filmRequest.getDescription());
            tempFilm.setReleaseYear(filmRequest.getReleaseYear());
            tempFilm.setLanguageId(filmRequest.getLanguageId());
            tempFilm.setOriginalLanguageId(filmRequest.getOriginalLanguageId());
            tempFilm.setRentalDuration(filmRequest.getRentalDuration());
            tempFilm.setRentalRate(filmRequest.getRentalRate());
            tempFilm.setLength(filmRequest.getLength());
            tempFilm.setReplacementCost(filmRequest.getReplacementCost());
            tempFilm.setRating(filmRequest.getRating());
            tempFilm.setSpecialFeatures(filmRequest.getSpecialFeatures());
            tempFilm.setLastUpdate(LocalDateTime.now());

            return ResponseEntity.status(HttpStatus.OK).body(filmRepository.save(tempFilm));
        }
        throw new NotFoundException("Cannot find film with id = " + theId);
    }

    @Override
    public ResponseEntity<String> deleteById(Integer theId) {
        boolean exists = filmRepository.existsById(theId);
        if (exists) {
            filmRepository.deleteById(theId);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted film successfully");
        } else {
            throw new NotFoundException("Cannot find film with id = " + theId);
        }
    }
}
