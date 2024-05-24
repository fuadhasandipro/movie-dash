package frames;

import javax.swing.*;
import entities.*;
import repositories.*;
import java.time.LocalDate;
import java.util.UUID;
import java.awt.event.*;

public class BookingStepsDialog extends JDialog {

    private Screening selectedScreening;
    private int numberOfTickets;
    private JTextField numberOfTicketsField;
    private JTabbedPane tabbedPane;
    private User currentUser;
    private Reservation reservation;

    public BookingStepsDialog(JFrame parentFrame, User currentUser) {
        super(parentFrame, "Book a Seat", true);
        this.currentUser = currentUser;
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel step1Panel = createStep1Panel();
        JPanel step2Panel = createStep2Panel();
        JPanel step3Panel = createStep3Panel();

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Step 1: Choose Screening", step1Panel);
        tabbedPane.addTab("Step 2: Make Payment", step2Panel);
        tabbedPane.addTab("Step 3: Confirm Reservation", step3Panel);

        getContentPane().add(tabbedPane);
    }

    private JPanel createStep1Panel() {
        JPanel panel = new JPanel();

        ScreeningRepo screeningRepo = new ScreeningRepo();
        Screening[] screenings = screeningRepo.getAllScreenings();

        String[] screeningDisplayStrings = new String[screenings.length];

        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null) {
                screeningDisplayStrings[i] = screenings[i].getDisplayString();
            }
        }

        JComboBox<String> screeningComboBox = new JComboBox<>(screeningDisplayStrings);

        panel.add(new JLabel("Select Screening:"));
        panel.add(screeningComboBox);

        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleNextButtonAction(screeningComboBox, screenings);
            }
        });
        panel.add(nextButton);

        return panel;
    }

    private void handleNextButtonAction(JComboBox<String> screeningComboBox, Screening[] screenings) {
        String selectedScreeningDisplay = (String) screeningComboBox.getSelectedItem();

        Screening selectedScreening = null;
        for (Screening screening : screenings) {
            if (screening != null && screening.getDisplayString().equals(selectedScreeningDisplay)) {
                selectedScreening = screening;
                break;
            }
        }

        if (selectedScreening != null) {
            this.selectedScreening = selectedScreening;
            tabbedPane.setSelectedIndex(1);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid screening.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createStep2Panel() {
        JPanel panel = new JPanel();

        numberOfTicketsField = new JTextField(10);
        panel.add(new JLabel("Number of Tickets:"));
        panel.add(numberOfTicketsField);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handlePayButtonAction();
            }
        });
        panel.add(payButton);

        return panel;
    }

    private void handlePayButtonAction() {
        try {
            numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());

            double totalPrice = numberOfTickets * selectedScreening.getTicketPrice();

            reservation = new Reservation();
            reservation.setReservationId(UUID.randomUUID().toString());
            reservation.setScreeningId(selectedScreening.getScreeningId());
            reservation.setCustomerId(this.currentUser.getUserId());
            reservation.setNumberOfTickets(numberOfTickets);
            reservation.setTotalPrice(totalPrice);
            reservation.setReservationDate(LocalDate.now());

            ReservationRepo reservationRepo = new ReservationRepo();
            reservationRepo.addReservation(reservation);

            Payment payment = new Payment();
            payment.setPaymentId(UUID.randomUUID().toString());
            payment.setReservationId(reservation.getReservationId());
            payment.setAmount(totalPrice);
            payment.setPaymentDate(LocalDate.now());

            PaymentRepo paymentRepo = new PaymentRepo();
            paymentRepo.addPayment(payment);

            tabbedPane.setSelectedIndex(2);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number of tickets.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createStep3Panel() {
        JPanel panel = new JPanel();

        JButton confirmButton = new JButton("Confirm Reservation");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleConfirmButtonAction();
            }
        });
        panel.add(confirmButton);

        return panel;
    }

    private void handleConfirmButtonAction() {
        if (selectedScreening == null) {
            JOptionPane.showMessageDialog(this, "Please select a screening first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Reservation confirmed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
