package com.group06.sakila.service.actor_service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.ErrorResponse;
import com.group06.sakila.repository.ActorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;

    @Override
    public ResponseEntity<ErrorResponse> findAll() {

        List<Actor> foundActors = actorRepository.findAll();
        return (!foundActors.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ErrorResponse(HttpStatus.OK.value(), "Query actors successfully", foundActors)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Cannot find any actor in list", "")
                );
    }

    @Override
    public ResponseEntity<ErrorResponse> findById(Long theId) { // Optional mean the result can be null
        Optional<Actor> foundActor = actorRepository.findById(theId);
        return (foundActor.isPresent()) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ErrorResponse(HttpStatus.OK.value(), "Query actor successfully", foundActor)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Cannot find actor with id = " + theId, "")
                );
    }

    @Override
    public ResponseEntity<ErrorResponse> createActor(Actor theActor) {
        try {
            return ResponseEntity.status(HttpStatus.OK). body(
                    new ErrorResponse(HttpStatus.OK.value(), "Created actor successfully", actorRepository.save(theActor))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), "")
            );
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> updateActor(Actor theActor, Long theId) {
        Actor tempActor = actorRepository.findById(theId).orElse(null);

        if (tempActor != null) {
            tempActor.setFirstName(theActor.getFirstName());
            tempActor.setLastName(theActor.getLastName());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ErrorResponse(HttpStatus.OK.value(), "Updated actor successfully", actorRepository.save(tempActor))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Cannot find actor with id = " + theId, "")
            );
        }
    }

    @Override
    public ResponseEntity<ErrorResponse> deleteById(Long theId) {
        boolean exists = actorRepository.existsById(theId);
        if (exists) {
            actorRepository.deleteById(theId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ErrorResponse(HttpStatus.OK.value(), "Deleted actor successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Cannot find actor with id = " + theId, "")
            );
        }
    }
}
