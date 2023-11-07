package com.group06.sakila.service;

import com.group06.sakila.entity.Actor;
import com.group06.sakila.exception.NotFoundException;
import com.group06.sakila.repository.ActorRepository;
import com.group06.sakila.request_model.ActorRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {
    private ActorRepository actorRepository;
    private static Logger logger = LoggerFactory.getLogger(ActorServiceImpl.class);
    @Override
    public List<Actor> findAll() {
        logger.debug("This is find all actors method");
        List<Actor> foundActors = actorRepository.findAll();
        logger.info("Found {} actors.", foundActors.size());
        return foundActors;
    }

    @Override
    public Actor findById(Long theId) { // Optional mean the result can be null
        logger.debug("This is find actor by id method");
        Actor foundActor = actorRepository.findById(theId).orElse(null);

        if (foundActor == null) {
            logger.error("Cannot find actor with id = {}.", theId);
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
        logger.info("Found actor with id = {}.", theId);
        return foundActor;
    }

    @Override
    public Actor createActor(ActorRequest actorRequest) {
        logger.debug("This is create actor method");
        Actor actorSaved = Actor.builder()
                .firstName(actorRequest.getFirstName())
                .lastName(actorRequest.getLastName())
                .lastUpdate(LocalDateTime.now())
                .build();

        Actor newActor = actorRepository.save(actorSaved);
        logger.info("Created new actor: {}", actorRequest);
        return newActor;
    }

    @Override
    public Actor updateActor(ActorRequest actorRequest, Long theId) {
        logger.debug("This is update actor method");
        Actor tempActor = actorRepository.findById(theId).orElse(null);

        if (tempActor == null) {
            logger.error("Cannot find actor with id = {} for update.", theId);
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
        tempActor.setFirstName(actorRequest.getFirstName());
        tempActor.setLastName(actorRequest.getLastName());

        Actor savedActor = actorRepository.save(tempActor);
        logger.info("Updated actor with id = {}: {}", theId, actorRequest);
        return savedActor;
    }

    @Override
    public void deleteById(Long theId) {
        logger.debug("deleteById method called for actor with id = {}.", theId);
        boolean exists = actorRepository.existsById(theId);
        if (exists) {
            actorRepository.deleteById(theId);
            logger.info("Deleted actor with id = {}.", theId);
        } else {
            logger.error("Cannot find actor with id = {} for delete.", theId);
            throw new NotFoundException("Cannot find actor with id = " + theId);
        }
    }
}
