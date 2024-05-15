package repositories;

import entities.Movie;
import interfaces.IMovieRepo;

public class MovieRepo implements IMovieRepo {

    private static final String MOVIES_FILE_PATH = "repositories/db/movies.txt";
    private static final int MAX_MOVIES = 100;

    public void addMovie(Movie movie) {
        Movie[] movies = getAllMovies();

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null) {
                movies[i] = movie;
                break;
            }
        }

        writeMovies(movies);
    }

    public void removeMovie(String movieId) {
        Movie[] movies = getAllMovies();

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null && movies[i].getMovieId().equals(movieId)) {
                movies[i] = null;
                break;
            }
        }

        writeMovies(movies);
    }

    public void updateMovie(Movie movie) {
        Movie[] movies = getAllMovies();

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null && movies[i].getMovieId().equals(movie.getMovieId())) {
                movies[i] = movie;
                break;
            }
        }

        writeMovies(movies);
    }

    public Movie searchMovieByMovieId(String movieId) {
        Movie[] movies = getAllMovies();

        for (Movie movie : movies) {
            if (movie != null && movie.getMovieId().equals(movieId)) {
                return movie;
            }
        }
        return null;
    }

    public Movie[] searchMovieByGenreId(String genreId) {
        Movie[] movies = getAllMovies();
        Movie[] result = new Movie[MAX_MOVIES];
        int index = 0;
        for (Movie movie : movies) {
            if (movie != null && movie.getGenreId().equals(genreId)) {
                result[index] = movie;
                index++;
            }
        }

        return result;
    }

    public Movie[] searchMovieByDirectorId(String directorId) {
        Movie[] movies = getAllMovies();
        Movie[] result = new Movie[MAX_MOVIES];
        int index = 0;
        for (Movie movie : movies) {
            if (movie != null && movie.getDirectorId().equals(directorId)) {
                result[index] = movie;
                index++;
            }
        }
        return result;
    }

    public Movie[] getAllMovies() {
        String[] data = new FileIO().readFile(MOVIES_FILE_PATH);
        Movie[] movies = new Movie[MAX_MOVIES];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Movie movie = new Movie().formMovie(str);
                movies[index] = movie;
                index++;
            }
        }
        return movies;
    }

    public void writeMovies(Movie[] movies) {
        String[] data = new String[MAX_MOVIES];
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                data[i] = movies[i].toString();
            }
        }
        new FileIO().writeFile(data, MOVIES_FILE_PATH);
    }
}
