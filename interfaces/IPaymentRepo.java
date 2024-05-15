package interfaces;
import entities.Payment;

public interface IPaymentRepo {
    void addPayment(Payment payment);
    void removePayment(String paymentId);
    void updatePayment(Payment payment);
    Payment searchPaymentById(String paymentId);
    Payment[] getPaymentsByReservationId(String reservationId);
    Payment[] getAllPayments();
}
