package frames;

import javax.swing.*;
import java.awt.*;
import entities.*;
import repositories.*;

public class CustomerPaymentsPanel extends JPanel {
    private User currentUser;
    private JPanel paymentPanel;
    private PaymentRepo paymentRepo;
    private ReservationRepo reservationRepo;

    public CustomerPaymentsPanel(User currentUser) {
        this.currentUser = currentUser;
        this.paymentRepo = new PaymentRepo();
        this.reservationRepo = new ReservationRepo();

        setBackground(new Color(0x201f2d));
        setLayout(null);

        JLabel headerTitle = new JLabel();
        headerTitle.setText("Payments by " + currentUser.getName());
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
        headerTitle.setBounds(30, 40, 300, headerTitle.getPreferredSize().height);

        paymentPanel = createPaymentPanel();
        JScrollPane scrollPane = new JScrollPane(paymentPanel);
        scrollPane.setBounds(30, 105, 660, 325);
        add(scrollPane);
    }

    private JPanel createPaymentPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x3a3854));
        panel.setLayout(null);

        Reservation[] reservations = reservationRepo.getReservationsByCustomerId(currentUser.getUserId());
        
        if (reservations.length == 0) {
            JLabel noPaymentLabel = new JLabel("No payments found.");
            noPaymentLabel.setForeground(Color.WHITE);
            noPaymentLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noPaymentLabel.setBounds(0, 0, 660, 30);
            panel.add(noPaymentLabel);
        } else {
            int yOffset = 10;
            for (Reservation reservation : reservations) {
                if (reservation != null) {

                    Payment[] payments = paymentRepo.getPaymentsByReservationId(reservation.getReservationId());
                    for (Payment payment : payments) {
                        if(payment != null) {
                            JPanel paymentCard = createPaymentCard(payment, reservation);
                            paymentCard.setBounds(10, yOffset, 640, 100);
                            panel.add(paymentCard);
                            yOffset += 110;
                        }
                    }
                }
            }
            panel.setPreferredSize(new Dimension(660, yOffset));
        }
        return panel;
    }

    private JPanel createPaymentCard(Payment payment, Reservation reservation) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(0x2e2d3d));

        JLabel reservationLabel = new JLabel("Reservation ID: " + reservation.getReservationId());
        reservationLabel.setForeground(Color.WHITE);
        reservationLabel.setBounds(10, 10, 300, 20);
        card.add(reservationLabel);

        JLabel amountLabel = new JLabel("Amount: $" + payment.getAmount());
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(10, 40, 100, 20);
        card.add(amountLabel);

        JLabel paymentDateLabel = new JLabel("Date: " + payment.getPaymentDate());
        paymentDateLabel.setForeground(Color.WHITE);
        paymentDateLabel.setBounds(10, 70, 200, 20);
        card.add(paymentDateLabel);

        return card;
    }
}
