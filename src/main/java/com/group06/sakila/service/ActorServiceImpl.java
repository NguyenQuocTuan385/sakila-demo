package com.group06.sakila.service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.NotFoundException;
import com.group06.sakila.repository.ActorRepository;
import com.group06.sakila.requestmodel.ActorRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;

    @Override
    public ResponseEntity<List<Actor>> findAll() {
        List<Actor> foundActors = actorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(foundActors);
    }

    @Override
    public ResponseEntity<Actor> findById(Long theId) { // Optional mean the result can be null
        Actor foundActor = actorRepository.findById(theId).orElse(null);
        if (foundActor != null) {
            return ResponseEntity.status(HttpStatus.OK).body(foundActor);
        }
        throw new NotFoundException("Cannot find actor with id = " + theId);
    }

    @Override
    public ResponseEntity<Actor> createActor(ActorRequest actorRequest) {
        Actor actorSaved = new Actor();
        actorSaved.setFirstName(actorRequest.getFirstName());
        actorSaved.setLastName(actorRequest.getLastName());
        actorSaved.setLastUpdate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(actorRepository.save(actorSaved));
    }

    @Override
    public ResponseEntity<Actor> updateActor(ActorRequest actorRequest, Long theId) {
        Actor tempActor = actorRepository.findById(theId).orElse(null);

        if (tempActor != null) {
            tempActor.setFirstName(actorRequest.getFirstName());
            tempActor.setLastName(actorRequest.getLastName());
            return ResponseEntity.status(HttpStatus.OK).body(actorRepository.save(tempActor));
        }
        throw new NotFoundException("Cannot find actor with id = " + theId);
    }

    @Override
    public ResponseEntity<String> deleteById(Long theId) {
        boolean exists = actorRepository.existsById(theId);
        if (exists) {
            actorRepository.deleteById(theId);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted actor successfully");
        } else {
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
    }
}
