package interfaces;
import entities.Movie;

public interface IMovieRepo {
    void addMovie(Movie movie);
	void removeMovie(String movieId);
	void updateMovie(Movie movie);
    Movie searchMovieByMovieId(String movieId);
    Movie[] searchMovieByGenreId(String genreId);
    Movie[] getAllMovies();
}
