package com.group06.sakila.service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.request_model.ActorRequest;

import java.util.List;


public interface ActorService {
    List<Actor> findAll();

    Actor findById(Long theId);

    Actor createActor(ActorRequest actorRequest);

    Actor updateActor(ActorRequest actorRequest, Long theId);

    void deleteById(Long theId);
}
