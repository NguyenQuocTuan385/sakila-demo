package com.group06.sakila.service.film_service;

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
    public ResponseEntity<Film> createFilm(Film theFilm) {
        return ResponseEntity.status(HttpStatus.OK). body(filmRepository.save(theFilm)
        );
    }

    @Override
    public ResponseEntity<Film> updateFilm(Film theFilm, Integer theId) {
        Film tempFilm = filmRepository.findById(theId).orElse(null);

        if (tempFilm != null) {
            tempFilm.setTitle(theFilm.getTitle());
            tempFilm.setDescription(theFilm.getDescription());
            tempFilm.setReleaseYear(theFilm.getReleaseYear());
            tempFilm.setLanguageId(theFilm.getLanguageId());
            tempFilm.setOriginalLanguageId(theFilm.getOriginalLanguageId());
            tempFilm.setRentalRate(theFilm.getRentalRate());
            tempFilm.setLength(theFilm.getLength());
            tempFilm.setReplacementCost(theFilm.getReplacementCost());
            tempFilm.setRating(theFilm.getRating());
            tempFilm.setSpecialFeatures(theFilm.getSpecialFeatures());
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
            return ResponseEntity.status(HttpStatus.OK).body("Deleted actor successfully");
        } else {
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
    }
}
