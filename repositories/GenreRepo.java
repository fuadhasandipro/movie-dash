package repositories;
import entities.*;
import interfaces.*;

public class GenreRepo implements IGenreRepo {

    private static final String DB_PATH = "repositories/db/genres.txt";
    private static final int MAX_AMOUNT = 100; 

    public void addGenre(Genre genre) {
        Genre[] genreList = getAllGenres();

        for (int i = 0; i < genreList.length; i++) {
            if (genreList[i] == null) {
                genreList[i] = genre;
                break;
            }
        }

        write(genreList);
    }

    public void removeGenre(String genreId) {
        Genre[] genreList = getAllGenres();

        for (int i = 0; i < genreList.length; i++) {
            if (genreList[i] != null && genreList[i].getGenreId().equals(genreId)) {
                genreList[i] = null;
                break;
            }
        }

        write(genreList);
    }

    public void updateGenre(Genre genre) {
        Genre[] genreList = getAllGenres();

        for (int i = 0; i < genreList.length; i++) {
            if (genreList[i] != null && genreList[i].getGenreId().equals(genre.getGenreId())) {
                genreList[i] = genre;
                break;
            }
        }

        write(genreList);
    }

    public Genre searchGenreById(String genreId) {
        Genre[] genreList = getAllGenres();

        for (Genre genre : genreList) {
            if (genre != null && genre.getGenreId().equals(genreId)) {
                return genre;
            }
        }

        return null;
    }

    public Genre[] getAllGenres() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(DB_PATH);

        Genre[] genreList = new Genre[MAX_AMOUNT];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Genre genre = new Genre().formGenre(str);
                genreList[index] = genre;
                index++;
            }
        }

        return genreList;
    }

    public void write(Genre[] genreList) {
        String[] data = new String[MAX_AMOUNT];
        for (int i = 0; i < genreList.length; i++) {
            if (genreList[i] != null) {
                data[i] = genreList[i].toString();
            }
        }

        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, DB_PATH);
    }
}
