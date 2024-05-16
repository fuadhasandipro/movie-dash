package repositories;
import entities.*;
import interfaces.*;

public class ScreeningRepo implements IScreeningRepo {

    private static final String DB_PATH = "repositories/db/screenings.txt";
    private static final int MAX_AMOUNT = 100; 

    public void addScreening(Screening screening) {
        Screening[] screenings = getAllScreenings();
        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] == null) {
                screenings[i] = screening;
                break;
            }
        }
        write(screenings);
    }

    public void removeScreening(String screeningId) {
        Screening[] screenings = getAllScreenings();
        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null && screenings[i].getScreeningId().equals(screeningId)) {
                screenings[i] = null;
                break;
            }
        }
        write(screenings);
    }
    public void updateScreening(Screening screening) {
        Screening[] screenings = getAllScreenings();

        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null && screenings[i].getScreeningId().equals(screening.getScreeningId())) {
                screenings[i] = screening;
                break;
            }
        }
        write(screenings);
    }

    public Screening searchScreeningById(String screeningId) {
        Screening[] screenings = getAllScreenings();
        for (Screening screening : screenings) {
            if (screening != null && screening.getScreeningId().equals(screeningId)) {
                return screening;
            }
        }
        return null;
    }

    public Screening[] getScreeningsByMovieId(String movieId) {
        Screening[] allScreenings = getAllScreenings();
        Screening[] screeningsByMovieId = new Screening[MAX_AMOUNT];
        int index = 0;
        for (Screening screening : allScreenings) {
            if (screening != null && screening.getMovieId().equals(movieId)) {
                screeningsByMovieId[index] = screening;
                index++;
            }
        }
        return screeningsByMovieId;
    }

    public Screening[] getAllScreenings() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(DB_PATH);

        Screening[] screenings = new Screening[MAX_AMOUNT];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Screening screening = new Screening().formScreening(str);
                screenings[index] = screening;
                index++;
            }
        }
        return screenings;
    }

    private void write(Screening[] screenings) {
        String[] data = new String[MAX_AMOUNT];
        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null) {
                data[i] = screenings[i].toStringScreening();
            }
        }
        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, DB_PATH);
    }
}
