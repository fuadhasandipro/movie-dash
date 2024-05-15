package entities;
import java.time.LocalDate;

public class Reservation {
    private String reservationId, screeningId, customerId;
    private int numberOfTickets;
    private double totalPrice;
    private LocalDate reservationDate;

    public Reservation() {}

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(String screeningId) {
        this.screeningId = screeningId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String toStringReservation() {
        return reservationId + "," + screeningId + "," + customerId + "," + numberOfTickets + "," + totalPrice + "," + reservationDate + "\n";
    }

    public Reservation formReservation(String str) {
        String[] parts = str.split(",");
        Reservation reservation = new Reservation();
        reservation.setReservationId(parts[0]);
        reservation.setScreeningId(parts[1]);
        reservation.setCustomerId(parts[2]);
        reservation.setNumberOfTickets(Integer.parseInt(parts[3]));
        reservation.setTotalPrice(Double.parseDouble(parts[4]));
        reservation.setReservationDate(LocalDate.parse(parts[5]));
        return reservation;
    }
}
