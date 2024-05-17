package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import entities.*;
import repositories.*;

public class CustomerReservationPanel extends JPanel {
    private User currentUser;
    private JPanel reservationPanel;
    private ReservationRepo reservationRepo;
    private ScreeningRepo screeningRepo;
    private MovieRepo movieRepo;
    private JFrame parentFrame;

    public CustomerReservationPanel(User currentUser, JFrame parentFrame) {
        this.currentUser = currentUser;
        this.reservationRepo = new ReservationRepo();
        this.screeningRepo = new ScreeningRepo();
        this.movieRepo = new MovieRepo();
        this.parentFrame = parentFrame;

        setBackground(new Color(0x201f2d));
        setLayout(null);

        JLabel headerTitle = new JLabel();
        headerTitle.setText("Reservations of " + currentUser.getName());
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
        headerTitle.setBounds(30, 40, 300, headerTitle.getPreferredSize().height);

        reservationPanel = createReservationPanel();
        JScrollPane scrollPane = new JScrollPane(reservationPanel);
        scrollPane.setBounds(30, 105, 660, 325);
        add(scrollPane);

        JButton bookButton = new JButton("Book New Reservation");
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingStepsDialog bookingDialog = new BookingStepsDialog(parentFrame);
                bookingDialog.setVisible(true);
            }
        });
        bookButton.setBounds(30, 440, 200, 30);
        add(bookButton);
    }

    private JPanel createReservationPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x3a3854));
        panel.setLayout(null);

        Reservation[] reservations = reservationRepo.getReservationsByCustomerId(currentUser.getUserId());

        if (reservations.length == 0) {
            JLabel noReservationLabel = new JLabel("No reservations found.");
            noReservationLabel.setForeground(Color.WHITE);
            noReservationLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noReservationLabel.setBounds(0, 0, 660, 30);
            panel.add(noReservationLabel);
        } else {
            int yOffset = 10;
            for (Reservation reservation : reservations) {
                if (reservation != null) {
                    Screening screening = screeningRepo.searchScreeningById(reservation.getScreeningId());
                    Movie movie = movieRepo.searchMovieByMovieId(screening.getMovieId());
                    JPanel reservationCard = createReservationCard(reservation, screening, movie);
                    reservationCard.setBounds(10, yOffset, 640, 100);
                    panel.add(reservationCard);
                    yOffset += 110;
                }
            }
            panel.setPreferredSize(new Dimension(660, yOffset));
        }
        return panel;
    }

    private JPanel createReservationCard(Reservation reservation, Screening screening, Movie movie) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(0x2e2d3d));

        JLabel movieLabel = new JLabel("Movie: " + movie.getTitle());
        movieLabel.setForeground(Color.WHITE);
        movieLabel.setBounds(10, 10, 300, 20);
        card.add(movieLabel);

        JLabel dateLabel = new JLabel("Date: " + reservation.getReservationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(10, 40, 200, 20);
        card.add(dateLabel);

        JLabel ticketsLabel = new JLabel("Tickets: " + reservation.getNumberOfTickets());
        ticketsLabel.setForeground(Color.WHITE);
        ticketsLabel.setBounds(10, 70, 100, 20);
        card.add(ticketsLabel);

        JLabel locationLabel = new JLabel("Location: " + screening.getLocation());
        locationLabel.setForeground(Color.WHITE);
        locationLabel.setBounds(320, 10, 300, 20);
        card.add(locationLabel);

        JLabel timeLabel = new JLabel("Time: " + screening.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(320, 40, 100, 20);
        card.add(timeLabel);

        return card;
    }
}
