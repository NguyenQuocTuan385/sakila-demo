package com.group06.sakila.controllers;

import com.group06.sakila.entities.Actor;
import com.group06.sakila.request_models.ActorRequest;
import com.group06.sakila.services.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping("${api.prefix}/actors")
@Tag(name = "Actors API")
public class ActorController {
    private ActorService actorService;

    // Get all actors
    @Operation(summary = "Get all actors")
    @ApiResponse(responseCode = "200", description = "List of actors")
    @GetMapping("")
    ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findAll());
    }

    //Get detail actor
    @Operation(summary = "Get actor by ID")
    @ApiResponse(responseCode = "200", description = "Found the actor")
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @GetMapping("/{id}")
    ResponseEntity<Actor> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.findById(id));
    }

    //Create actor
    @Operation(summary = "Create a new actor")
    @ApiResponse(responseCode = "201", description = "Actor is created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping("")
    ResponseEntity<Actor> createActor(@RequestBody @Valid ActorRequest actorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.createActor(actorRequest));
    }

    //Update actor
    @Operation(
            parameters = {@Parameter(name = "id", description = "This is the id of student that we want to update", example = "3")},
            summary = "Update actor with ID",
            description = "Update the actor with the matching input ID given by client",
            responses = {@ApiResponse(responseCode = "200", description = "Updated Actor",
                    content = {@Content(mediaType = "application/json",
                            examples = @ExampleObject(value = """
                               {
                                  "id": 3,
                                  "firstName": "Tien",
                                  "lastName": "Truong",
                                  "lastUpdate": "2023-10-25T17:13:05.779Z"
                                }
                                                       """)),
                            @Content(mediaType = "text/plain",
                                    examples = @ExampleObject(value = """
                                            id: 3,firstName: Tien, lastName: Truong, lastUpdate: 2023-10-25T17:13:05.779Z"""))}),
                    @ApiResponse(responseCode = "404", description = "Not Found Actor",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = """
                                               {
                                                  "status": 404,
                                                  "message": "Cannot find actor with provided id",
                                                  "errorDetails": null
                                               }
                                           """))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid parameter value",
                            content = @Content(mediaType = "application/json",
                                    examples = @ExampleObject(value = """
                                          {
                                              "status": 400,
                                              "message": "Invalid parameter value",
                                              "errorDetails": {}
                                          }
                                       """)))
            }
    )

    @PutMapping("/{id}")
    ResponseEntity<Actor> updateActor(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "This is description for response",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = """
                           {
                                   "firstName": "Tien",
                                   "lastName": "Truong"
                           }
                       """)
            )) @RequestBody @Valid ActorRequest actorRequest,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(actorService.updateActor(actorRequest, id));
    }

    // Delete actor
    @Operation(summary = "Delete actor by ID")
    @ApiResponse(responseCode = "204", description = "Actor is deleted")
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @DeleteMapping("/{id}")
    ResponseEntity<String > deleteActor(@PathVariable Long id) {
        actorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted actor successfully");
    }
}
