package repositories;

import entities.Payment;
import interfaces.IPaymentRepo;

public class PaymentRepo implements IPaymentRepo {

    private static final String DB_PATH = "repositories/db/payments.txt";
    private static final int MAX_AMOUNT = 100;

    public void addPayment(Payment payment) {
        Payment[] payments = getAllPayments();
        for (int i = 0; i < payments.length; i++) {
            if (payments[i] == null) {
                payments[i] = payment;
                break;
            }
        }
        write(payments);
    }

    public void removePayment(String paymentId) {
        Payment[] payments = getAllPayments();
        for (int i = 0; i < payments.length; i++) {
            if (payments[i] != null && payments[i].getPaymentId().equals(paymentId)) {
                payments[i] = null;
                break;
            }
        }
        write(payments);
    }

    public void updatePayment(Payment payment) {
        Payment[] payments = getAllPayments();
        for (int i = 0; i < payments.length; i++) {
            if (payments[i] != null && payments[i].getPaymentId().equals(payment.getPaymentId())) {
                payments[i] = payment;
                break;
            }
        }
        write(payments);
    }

    public Payment searchPaymentById(String paymentId) {
        Payment[] payments = getAllPayments();
        for (Payment payment : payments) {
            if (payment != null && payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public Payment[] getPaymentsByReservationId(String reservationId) {
        Payment[] allPayments = getAllPayments();
        Payment[] paymentsByReservationId = new Payment[MAX_AMOUNT];
        int index = 0;
        for (Payment payment : allPayments) {
            if (payment != null && payment.getReservationId().equals(reservationId)) {
                paymentsByReservationId[index] = payment;
                index++;
            }
        }
        return paymentsByReservationId;
    }

    public Payment[] getAllPayments() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(DB_PATH);

        Payment[] payments = new Payment[MAX_AMOUNT];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Payment payment = new Payment().formPayment(str);
                payments[index] = payment;
                index++;
            }
        }
        return payments;
    }

    private void write(Payment[] payments) {
        String[] data = new String[MAX_AMOUNT];
        for (int i = 0; i < payments.length; i++) {
            if (payments[i] != null) {
                data[i] = payments[i].toStringPayment();
            }
        }
        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, DB_PATH);
    }
}
