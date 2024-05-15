package interfaces;
import entities.Reservation;

public interface IReservationRepo {
    void addReservation(Reservation reservation);
    void removeReservation(String reservationId);
    void updateReservation(Reservation reservation);
    Reservation searchReservationById(String reservationId);
    Reservation[] getReservationsByCustomerId(String customerId);
    Reservation[] getAllReservations();
}
