package com.group06.sakila.service.actor_service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;


public interface ActorService {
    ResponseEntity<ErrorResponse> findAll();

    ResponseEntity<ErrorResponse> findById(Long theId);

    ResponseEntity<ErrorResponse> createActor(Actor theActor);

    ResponseEntity<ErrorResponse> updateActor(Actor theActor, Long theId);

    ResponseEntity<ErrorResponse> deleteById(Long theId);
}
