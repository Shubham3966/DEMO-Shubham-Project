package MCM_Project.MCM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MCM_Project.MCM.Entities.Actor;
import MCM_Project.MCM.Repository.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
