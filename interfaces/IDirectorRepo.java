package interfaces;
import entities.Director;

public interface IDirectorRepo {
    void addDirector(Director director);
    void removeDirector(String directorId);
    void updateDirector(Director director);
    Director searchDirectorById(String directorId);
    Director[] getAllDirectors();
}
