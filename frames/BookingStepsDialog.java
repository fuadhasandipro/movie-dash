package frames;

import javax.swing.*;
import entities.*;
import repositories.*;
import java.time.LocalDate;
import java.util.UUID;

public class BookingStepsDialog extends JDialog {

    private Screening selectedScreening;
    private int numberOfTickets;
    JTextField numberOfTicketsField;

    public BookingStepsDialog(JFrame parentFrame) {
        super(parentFrame, "Book a Seat", true);
        setSize(400, 300);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel step1Panel = createStep1Panel();
        JPanel step2Panel = createStep2Panel();
        JPanel step3Panel = createStep3Panel();

        JTabbedPane tabbedPane = new JTabbedPane();
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
        nextButton.addActionListener(e -> {
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
                JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, panel);
                tabbedPane.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid screening.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(nextButton);

        return panel;
    }

    private JPanel createStep2Panel() {
        JPanel panel = new JPanel();

        // UI components for payment details
        numberOfTicketsField = new JTextField(10);
        panel.add(new JLabel("Number of Tickets:"));
        panel.add(numberOfTicketsField);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(e -> {
            try {
                numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());
                
                // Calculate total price based on selected screening and number of tickets
                double totalPrice = numberOfTickets * selectedScreening.getTicketPrice();

                // Create a new Payment object
                Payment payment = new Payment();
                payment.setPaymentId(UUID.randomUUID().toString()); // Generate a unique payment ID
                payment.setReservationId(selectedScreening.getScreeningId()); // Use screening ID as reservation ID for simplicity
                payment.setAmount(totalPrice);
                payment.setPaymentDate(LocalDate.now());

                // Add payment to the PaymentRepo
                PaymentRepo paymentRepo = new PaymentRepo();
                paymentRepo.addPayment(payment);

                // Switch to the next tab (index 2: Confirmation)
                JTabbedPane tabbedPane = (JTabbedPane) SwingUtilities.getAncestorOfClass(JTabbedPane.class, panel);
                tabbedPane.setSelectedIndex(2); // Switch to the next tab (Confirmation)
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of tickets.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(payButton);

        return panel;
    }



    private JPanel createStep3Panel() {
        JPanel panel = new JPanel();

        JButton confirmButton = new JButton("Confirm Reservation");
        confirmButton.addActionListener(e -> {
            if (selectedScreening == null) {
                JOptionPane.showMessageDialog(this, "Please select a screening first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                // Retrieve the number of tickets entered in step 2
                int numberOfTickets = Integer.parseInt(numberOfTicketsField.getText());

                // Create a reservation using the selected screening and input values
                Reservation reservation = new Reservation();
                reservation.setReservationId(UUID.randomUUID().toString());
                reservation.setScreeningId(selectedScreening.getScreeningId());
                reservation.setCustomerId("12345"); // Set the customer ID (replace with actual logic to fetch customer ID)
                reservation.setNumberOfTickets(numberOfTickets);
                reservation.setTotalPrice(numberOfTickets * selectedScreening.getTicketPrice());
                reservation.setReservationDate(LocalDate.now());

                // Add the reservation to the repository
                ReservationRepo reservationRepo = new ReservationRepo();
                reservationRepo.addReservation(reservation);

                // Show success message
                JOptionPane.showMessageDialog(this, "Reservation confirmed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Close the dialog after reservation is confirmed
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of tickets.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(confirmButton);

        return panel;
    }

}
