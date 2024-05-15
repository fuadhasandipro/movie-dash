package interfaces;
import entities.Screening;

public interface IScreeningRepo {
    void addScreening(Screening screening);
    void removeScreening(String screeningId);
    void updateScreening(Screening screening);
    Screening searchScreeningById(String screeningId);
    Screening[] getScreeningsByMovieId(String movieId);
    Screening[] getAllScreenings();
}
