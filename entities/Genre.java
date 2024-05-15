package entities;

public class Genre {
    private String genreId;
    private String genreName;

    public Genre() {}

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String toStringGenre() {
        return genreId + "," + genreName + "\n";
    }

    public Genre formGenre(String str) {
        String[] parts = str.split(",");
        Genre genre = new Genre();
        genre.setGenreId(parts[0]);
        genre.setGenreName(parts[1]);
        return genre;
    }
}
