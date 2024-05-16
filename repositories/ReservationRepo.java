package repositories;

import entities.Reservation;
import interfaces.IReservationRepo;

public class ReservationRepo implements IReservationRepo {

    private static final String DB_PATH = "repositories/db/reservations.txt";
    private static final int MAX_AMOUNT = 100;

    public void addReservation(Reservation reservation) {
        Reservation[] reservations = getAllReservations();
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] == null) {
                reservations[i] = reservation;
                break;
            }
        }
        write(reservations);
    }

    public void removeReservation(String reservationId) {
        Reservation[] reservations = getAllReservations();
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null && reservations[i].getReservationId().equals(reservationId)) {
                reservations[i] = null;
                break;
            }
        }
        write(reservations);
    }

    public void updateReservation(Reservation reservation) {
        Reservation[] reservations = getAllReservations();
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null && reservations[i].getReservationId().equals(reservation.getReservationId())) {
                reservations[i] = reservation;
                break;
            }
        }
        write(reservations);
    }

    public Reservation searchReservationById(String reservationId) {
        Reservation[] reservations = getAllReservations();
        for (Reservation reservation : reservations) {
            if (reservation != null && reservation.getReservationId().equals(reservationId)) {
                return reservation;
            }
        }
        return null;
    }

    public Reservation[] getReservationsByCustomerId(String customerId) {
        Reservation[] allReservations = getAllReservations();
        Reservation[] reservationsByCustomerId = new Reservation[MAX_AMOUNT];
        int index = 0;
        for (Reservation reservation : allReservations) {
            if (reservation != null && reservation.getCustomerId().equals(customerId)) {
                reservationsByCustomerId[index] = reservation;
                index++;
            }
        }
        return reservationsByCustomerId;
    }

    public Reservation[] getAllReservations() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(DB_PATH);

        Reservation[] reservations = new Reservation[MAX_AMOUNT];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Reservation reservation = new Reservation().formReservation(str);
                reservations[index] = reservation;
                index++;
            }
        }
        return reservations;
    }

    private void write(Reservation[] reservations) {
        String[] data = new String[MAX_AMOUNT];
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                data[i] = reservations[i].toStringReservation();
            }
        }
        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, DB_PATH);
    }
}
