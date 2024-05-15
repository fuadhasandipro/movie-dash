package entities;
import java.time.LocalDate;

public class Payment {
    private String paymentId, reservationId;
    private double amount;
    private LocalDate paymentDate;

    public Payment() {}

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String toStringPayment() {
        return paymentId + "," + reservationId + "," + amount + "," + paymentDate + "\n";
    }

    public Payment formPayment(String str) {
        String[] parts = str.split(",");
        Payment payment = new Payment();
        payment.setPaymentId(parts[0]);
        payment.setReservationId(parts[1]);
        payment.setAmount(Double.parseDouble(parts[2]));
        payment.setPaymentDate(LocalDate.parse(parts[3]));
        return payment;
    }
}
