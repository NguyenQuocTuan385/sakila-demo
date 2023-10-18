package com.group06.sakila.service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.ErrorResponse;
import com.group06.sakila.requestmodel.ActorRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ActorService {
    ResponseEntity<List<Actor>> findAll();

    ResponseEntity<Actor> findById(Long theId);

    ResponseEntity<Actor> createActor(ActorRequest actorRequest);

    ResponseEntity<Actor> updateActor(ActorRequest actorRequest, Long theId);

    ResponseEntity<String> deleteById(Long theId);
}
