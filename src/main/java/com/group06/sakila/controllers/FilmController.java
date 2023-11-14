package com.group06.sakila.controllers;

import com.group06.sakila.request_models.FilmRequest;
import com.group06.sakila.entities.Film;
import com.group06.sakila.services.ServerBService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/filmsA")
@Tag(name = "Films API")
public class FilmController {

    private ServerBService serverBService;
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
    ResponseEntity<?> getAllFilms() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(
                serverBService.getAllFilms()
        );
    }

    @Operation(
            summary = "Get film by ID",
            parameters = {@Parameter(name = "id", description = "The film id", example = "3")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found the film",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            value = """
                                                    {
                                                      "id": 3,
                                                      "title": "SLIPPER FIDELITY",
                                                      "description": "A Taut Reflection of a Secret Agent And a Man who must Redeem a Explorer in A MySQL Convention",
                                                      "releaseYear": 2023,
                                                      "languageId": 1,
                                                      "originalLanguageId": 1,
                                                      "rentalDuration": 4,
                                                      "rentalRate": 2.99,
                                                      "length": 132,
                                                      "replacementCost": 27.99,
                                                      "rating": "PG-13",
                                                      "specialFeatures": "Commentaries,Deleted Scenes",
                                                      "lastUpdate": "2006-02-15 05:03:42"                                            
                                                    }
                                                    """
                                    )
                            )

                    ),
                    @ApiResponse(responseCode = "404", ref = "resourceNotFoundAPI"),
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(
                serverBService.getFilmById(id)
        );
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
    ResponseEntity<?> createFilm(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    value = """
                                        {
                                          "title": "SLIPPER FIDELITY",
                                          "description": "A Taut Reflection of a Secret Agent And a Man who must Redeem a Explorer in A MySQL Convention",
                                          "releaseYear": 2023,
                                          "languageId": 1,
                                          "originalLanguageId": 1,
                                          "rentalDuration": 4,
                                          "rentalRate": 2.99,
                                          "length": 132,
                                          "replacementCost": 27.99,
                                          "rating": "PG-13",
                                          "specialFeatures": "Commentaries,Deleted Scenes"
                                        }
                                        """
                            )
                    }
            )
    ) @RequestBody @Valid FilmRequest filmRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                "Get all films"
        );
    }

    @Operation(
            summary = "Update film by ID",
            parameters = {@Parameter(name = "id", description = "The film id", example = "3")},
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
    ResponseEntity<?> updateFilm(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    value = """
                                            {
                                              "title": "SLIPPER FIDELITY",
                                              "description": "A Taut Reflection of a Secret Agent And a Man who must Redeem a Explorer in A MySQL Convention",
                                              "releaseYear": 2023,
                                              "languageId": 1,
                                              "originalLanguageId": 1,
                                              "rentalDuration": 4,
                                              "rentalRate": 2.99,
                                              "length": 132,
                                              "replacementCost": 27.99,
                                              "rating": "PG-13",
                                              "specialFeatures": "Commentaries,Deleted Scenes"
                                            }
                                            """
                            )
                    }
            )
    ) @RequestBody @Valid FilmRequest filmRequest, @PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                "Get all films"
        );
    }

    // Delete film
    @Operation(
            summary = "Delete film by ID",
            parameters = {@Parameter(name = "id", description = "The film id", example = "3")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted the film",
                            content = @Content(
                                    examples = @ExampleObject(
                                            value = "Deleted film successfully"
                                    )
                            )

                    ),
                    @ApiResponse(responseCode = "404", ref = "resourceNotFoundAPI"),
            })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFilm(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                "Get all films"
        );
    }
}
