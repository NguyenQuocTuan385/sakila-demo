package com.group06.sakila.controller;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.requestmodel.ActorRequest;
import com.group06.sakila.service.ActorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/actors")
public class ActorController {
    private ActorService actorService;

    // Get all actors
    @GetMapping("")
    ResponseEntity<List<Actor>> getAllActors() {
        return actorService.findAll();
    }

    //Get detail actor
    @GetMapping("/{id}")
    ResponseEntity<Actor> findById(@PathVariable Long id) {
        return actorService.findById(id);
    }

    //Create actor
    @PostMapping("")
    ResponseEntity<Actor> createActor(@RequestBody @Valid ActorRequest actorRequest) {
        return actorService.createActor(actorRequest);
    }

    //Update actor
    @PutMapping("/{id}")
    ResponseEntity<Actor> updateActor(@RequestBody @Valid ActorRequest actorRequest, @PathVariable Long id) {
        return actorService.updateActor(actorRequest, id);
    }

    // Delete actor
    @DeleteMapping("/{id}")
    ResponseEntity<String > deleteActor(@PathVariable Long id) {
        return actorService.deleteById(id);
    }
}
