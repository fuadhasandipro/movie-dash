package entities;
import java.time.LocalDateTime;

public class Screening {
    private String screeningId, movieId, location;
    private LocalDateTime startTime, endTime;
    private int availableSeats;
    private double ticketPrice;

    public Screening() {}

    public String getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(String screeningId) {
        this.screeningId = screeningId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String toStringScreening() {
        return screeningId + "," + movieId + "," + location + "," + startTime + "," + endTime + "," + availableSeats + "," + ticketPrice + "\n";
    }

    public Screening formScreening(String str) {
        String[] parts = str.split(",");
        Screening screening = new Screening();
        screening.setScreeningId(parts[0]);
        screening.setMovieId(parts[1]);
        screening.setLocation(parts[2]);
        screening.setStartTime(LocalDateTime.parse(parts[3]));
        screening.setEndTime(LocalDateTime.parse(parts[4]));
        screening.setAvailableSeats(Integer.parseInt(parts[5]));
        screening.setTicketPrice(Double.parseDouble(parts[6]));
        return screening;
    }
}
