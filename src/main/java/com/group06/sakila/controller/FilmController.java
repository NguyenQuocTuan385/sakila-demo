package com.group06.sakila.controller;

import com.group06.sakila.dto.response.SuccessResponse;
import com.group06.sakila.entity.Film;
import com.group06.sakila.service.film_service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/films")
@Tag(name = "Films API")
public class FilmController {
    private FilmService filmService;

    @Operation(
            summary = "Get all films",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of films",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Film.class))
                                    )
                    )
            }
    )
    @GetMapping("")
    ResponseEntity<SuccessResponse> getAllFilms() {
        return filmService.findAll();
    }

    @Operation(
            summary = "Get film by ID",
            parameters = {@Parameter(name = "id", description = "The film id", example = "3")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the film",
                            content = @Content(
                                    schema = @Schema(implementation = Film.class),
                                    mediaType = "application/json"
                            )

                    ),
                    @ApiResponse(responseCode = "404", ref = "resourceNotFoundAPI"),
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<SuccessResponse> findById(@PathVariable Integer id) {
        return filmService.findById(id);
    }

    //Create film
    @Operation(
            summary = "Create a new film",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Created the film",
                            content = @Content(
                                    schema = @Schema(implementation = Film.class),
                                    mediaType = "application/json"
                            )

                    ),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI"),
    })
    @PostMapping("")
    ResponseEntity<SuccessResponse> createFilm(@RequestBody @Valid Film theFilm) {
        return filmService.createFilm(theFilm);
    }

    @Operation(
            summary = "Update film by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated the film",
                            content = @Content(
                                    schema = @Schema(implementation = Film.class),
                                    mediaType = "application/json"
                            )

                    ),
                    @ApiResponse(responseCode = "404", ref = "resourceNotFoundAPI"),
                    @ApiResponse(responseCode = "400", ref = "badRequestAPI"),
            })
    @PutMapping("/{id}")
    ResponseEntity<SuccessResponse> updateActor(@RequestBody @Valid Film theFilm, @PathVariable Integer id) {
        return filmService.updateFilm(theFilm, id);
    }

    // Delete film
    @Operation(
            summary = "Delete film by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted the film",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = "{\"status\": 200, \"message\": \"Deleted film successfully\", \"data\": null}"
                                    )
                            )

                    ),
                    @ApiResponse(responseCode = "404", ref = "resourceNotFoundAPI"),
            })
    @DeleteMapping("/{id}")
    ResponseEntity<SuccessResponse> deleteFilm(@PathVariable Integer id) {
        return filmService.deleteById(id);
    }
}
