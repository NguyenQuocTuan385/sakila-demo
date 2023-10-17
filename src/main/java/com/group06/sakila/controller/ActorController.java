package com.group06.sakila.controller;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.ErrorResponse;
import com.group06.sakila.service.actor_service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/actors")
@Tag(name = "Actors API")
public class ActorController {
    private ActorService actorService;

    // Get all actors
    @Operation(summary = "Get all actors")
    @ApiResponse(responseCode = "200", description = "List of actors")
    @GetMapping("")
    ResponseEntity<ErrorResponse> getAllActors() {
        return actorService.findAll();
    }

    //Get detail actor
    @Operation(summary = "Get actor by ID")
    @ApiResponse(responseCode = "200", description = "Found the actor")
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @GetMapping("/{id}")
    ResponseEntity<ErrorResponse> findById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    //Create actor
    @Operation(summary = "Create a new actor")
    @ApiResponse(responseCode = "201", description = "Actor is created")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @PostMapping("")
    ResponseEntity<ErrorResponse> createActor(@RequestBody @Valid Actor theActor) {
        return actorService.createActor(theActor);
    }

    //Update actor
    @Operation(summary = "Update actor by ID")
    @ApiResponse(responseCode = "200", description = "Updated the actor")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "actor not found")
    @PutMapping("/{id}")
    ResponseEntity<ErrorResponse> updateActor(@RequestBody @Valid Actor theActor, @PathVariable Long id) {
        return actorService.updateActor(theActor, id);
    }

    // Delete actor
    @Operation(summary = "Delete actor by ID")
    @ApiResponse(responseCode = "204", description = "Actor is deleted")
    @ApiResponse(responseCode = "404", description = "Actor not found")
    @DeleteMapping("/{id}")
    ResponseEntity<ErrorResponse> deleteActor(@PathVariable Long id) {
        return actorService.deleteById(id);
    }
}
