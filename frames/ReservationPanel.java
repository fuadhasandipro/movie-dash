package frames;

import javax.swing.*;
import java.awt.*;
import entities.*;
import repositories.*;

public class ReservationPanel extends JPanel {

    private JTable reservationTable;
    private JScrollPane reservationTableScrollPane;
    private ReservationRepo reservationRepo;
    private JLabel headerTitle;

    public ReservationPanel() {
        setBackground(new Color(0x201f2d));
        setLayout(null);
        reservationRepo = new ReservationRepo();
        initializeComponents();
        initializeReservationTable();
    }

    private void initializeComponents() {
        headerTitle = new JLabel("Reservations");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);
    }

    private void initializeReservationTable() {
        String[] reservationColumns = {"Reservation ID", "Screening ID", "Customer", "No. of Tickets", "Total Price", "Reservation Date"};

        UserRepo userRp = new UserRepo();

        Reservation[] reservations = reservationRepo.getAllReservations();
        String[][] reservationData = new String[reservations.length][6];

        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                User user = userRp.searchUserByUserId(reservations[i].getCustomerId());

                reservationData[i][0] = reservations[i].getReservationId();
                reservationData[i][1] = reservations[i].getScreeningId();

                if (user != null) {
                    reservationData[i][2] = user.getName();
                } else {
                    reservationData[i][2] = reservations[i].getCustomerId();
                }

                reservationData[i][3] = String.valueOf(reservations[i].getNumberOfTickets());
                reservationData[i][4] = String.valueOf(reservations[i].getTotalPrice());
                reservationData[i][5] = reservations[i].getReservationDate().toString();
            }
        }

        reservationTable = new JTable(reservationData, reservationColumns);
        reservationTable.setBackground(new Color(0xb8b3fc));
        reservationTableScrollPane = new JScrollPane(reservationTable);
        reservationTableScrollPane.setBounds(30, 50, 660, 500);
        reservationTable.setEnabled(false);
        add(reservationTableScrollPane);
    }
}
