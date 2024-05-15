package interfaces;
import entities.Genre;

public interface IGenreRepo {
    void addGenre(Genre genre);
    void removeGenre(String genreId);
    void updateGenre(Genre genre);
    Genre searchGenreById(String genreId);
    Genre[] getAllGenres();
}
