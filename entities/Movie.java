package entities;
import java.time.LocalDate;

public class Movie {
    private String movieId, genreId, directorId, title, description;
    private LocalDate releaseDate;
    private int duration;
    private String posterImage;

    public Movie() {}

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getDirectorId() {
        return directorId;
    }

    public void setDirectorId(String directorId) {
        this.directorId = directorId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String toStringMovie() {
        return movieId + "," + title + "," + description + "," + genreId + "," +
               directorId + "," + releaseDate + "," + duration + "," + posterImage + "\n";
    }

    public Movie formMovie(String str) {
        String[] parts = str.split(",");
        Movie movie = new Movie();
        movie.setMovieId(parts[0]);
        movie.setTitle(parts[1]);
        movie.setDescription(parts[2]);
        movie.setGenreId(parts[3]);
        movie.setDirectorId(parts[4]);
        movie.setReleaseDate(LocalDate.parse(parts[5]));
        movie.setDuration(Integer.parseInt(parts[6]));
        movie.setPosterImage(parts[7]);
        return movie;
    }
}
