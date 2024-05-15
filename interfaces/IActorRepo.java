package interfaces;
import entities.Actor;

public interface IActorRepo {
    void addActor(Actor actor);
    void removeActor(String actorId);
    void updateActor(Actor actor);
    Actor searchActorById(String actorId);
    Actor[] getAllActors();
}
