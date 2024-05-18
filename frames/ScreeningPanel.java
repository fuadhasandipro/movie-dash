package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import entities.*;
import repositories.*;

public class ScreeningPanel extends JPanel implements ActionListener {
    private JTextField searchField;
    private JLabel headerTitle;
    private JLabel searchLabel;
    private JButton searchButton;
    private JTable screeningTable;
    private JScrollPane screeningTableSP;

    // Screening fields
    private JLabel movieIdLabel;
    private JTextField movieIdField;
    private JLabel locationLabel;
    private JTextField locationField;
    private JLabel startTimeLabel;
    private JTextField startTimeField;
    private JLabel endTimeLabel;
    private JTextField endTimeField;
    private JLabel availableSeatsLabel;
    private JTextField availableSeatsField;
    private JLabel ticketPriceLabel;
    private JTextField ticketPriceField;

    private JButton addButton;
    private JButton fillFormButton;
    private JButton updateButton;

    private ScreeningRepo screeningRepo;
    private MovieRepo movieRepo;

    public ScreeningPanel() {
        screeningRepo = new ScreeningRepo();
        movieRepo = new MovieRepo();
        setBackground(new Color(0x201f2d));
        setLayout(null);

        headerTitle = new JLabel("Screenings");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);

        searchField = new JTextField();
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        searchField.setBounds(260, 10, 405, 25);
        add(searchField);

        searchLabel = new JLabel("Enter Screening to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        searchLabel.setBounds(260, 35, 200, 25);
        add(searchLabel);

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton = new JButton(searchIcon);
        searchButton.setBounds(670, 10, 30, 25);
        add(searchButton);

        // Screening fields
        movieIdLabel = createLabel("Movie ID");
        movieIdLabel.setBounds(30, 70, 60, 25);
        add(movieIdLabel);

        movieIdField = createTextField();
        movieIdField.setBounds(100, 70, 120, 25);
        add(movieIdField);

        locationLabel = createLabel("Location");
        locationLabel.setBounds(240, 70, 60, 25);
        add(locationLabel);

        locationField = createTextField();
        locationField.setBounds(310, 70, 120, 25);
        add(locationField);

        startTimeLabel = createLabel("Start Time");
        startTimeLabel.setBounds(450, 70, 100, 25);
        add(startTimeLabel);

        startTimeField = createTextField();
        startTimeField.setBounds(560, 70, 120, 25);
        add(startTimeField);

        endTimeLabel = createLabel("End Time");
        endTimeLabel.setBounds(30, 105, 80, 25);
        add(endTimeLabel);

        endTimeField = createTextField();
        endTimeField.setBounds(120, 105, 120, 25);
        add(endTimeField);

        availableSeatsLabel = createLabel("Available Seats");
        availableSeatsLabel.setBounds(260, 105, 120, 25);
        add(availableSeatsLabel);

        availableSeatsField = createTextField();
        availableSeatsField.setBounds(380, 105, 120, 25);
        add(availableSeatsField);

        ticketPriceLabel = createLabel("Ticket Price");
        ticketPriceLabel.setBounds(520, 105, 90, 25);
        add(ticketPriceLabel);

        ticketPriceField = createTextField();
        ticketPriceField.setBounds(610, 105, 80, 25);
        add(ticketPriceField);

        addButton = new JButton("Add Screening");
        addButton.setBounds(30, 140, 140, 25);
        addButton.addActionListener(this);
        add(addButton);

        fillFormButton = new JButton("Fill Form");
        fillFormButton.setBounds(180, 140, 120, 25);
        fillFormButton.addActionListener(this);
        add(fillFormButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(310, 140, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        String[] screeningColumns = {"Screening ID", "Movie ID", "Location", "Start Time", "End Time", "Available Seats", "Ticket Price"};
        Screening[] screenings = screeningRepo.getAllScreenings();
        String[][] screeningData = new String[screenings.length][7];

        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null) {
                screeningData[i][0] = screenings[i].getScreeningId();
                screeningData[i][1] = screenings[i].getMovieId();
                screeningData[i][2] = screenings[i].getLocation();
                screeningData[i][3] = screenings[i].getStartTime().toString();
                screeningData[i][4] = screenings[i].getEndTime().toString();
                screeningData[i][5] = String.valueOf(screenings[i].getAvailableSeats());
                screeningData[i][6] = String.valueOf(screenings[i].getTicketPrice());
            }
        }

        screeningTable = new JTable(screeningData, screeningColumns);
        screeningTable.setBackground(new Color(0xb8b3fc));
        screeningTableSP = new JScrollPane(screeningTable);
        screeningTableSP.setBounds(30, 180, 720, 325);
        add(screeningTableSP);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setFont(new Font("Rubik Light", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(0x3a3854));
        textField.setForeground(Color.white);
        textField.setCaretColor(Color.white);
        return textField;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String screeningId = UUID.randomUUID().toString();
            String movieId = movieIdField.getText().trim();
            String location = locationField.getText().trim();
            String startTimeStr = startTimeField.getText().trim();
            String endTimeStr = endTimeField.getText().trim();
            String availableSeatsStr = availableSeatsField.getText().trim();
            String ticketPriceStr = ticketPriceField.getText().trim();

            LocalDateTime startTime, endTime;
            int availableSeats;
            double ticketPrice;

            if(movieRepo.searchMovieByMovieId(movieId) == null) {
                JOptionPane.showMessageDialog(this, "Movie with this ID Doesn't Exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date-time format. Use YYYY-MM-DD HH:MM.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                availableSeats = Integer.parseInt(availableSeatsStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Available seats must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ticketPrice = Double.parseDouble(ticketPriceStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ticket price must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Screening screening = new Screening();
            screening.setScreeningId(screeningId);
            screening.setMovieId(movieId);
            screening.setLocation(location);
            screening.setStartTime(startTime);
            screening.setEndTime(endTime);
            screening.setAvailableSeats(availableSeats);
            screening.setTicketPrice(ticketPrice);

            screeningRepo.addScreening(screening);
            JOptionPane.showMessageDialog(this, "Screening added successfully!");

            movieIdField.setText("");
            locationField.setText("");
            startTimeField.setText("");
            endTimeField.setText("");
            availableSeatsField.setText("");
            ticketPriceField.setText("");
            refreshTable();
        }

        if (e.getSource() == fillFormButton) {
            int selectedRow = screeningTable.getSelectedRow();
            if (selectedRow != -1) {
                String screeningId = screeningTable.getValueAt(selectedRow, 0).toString();
                String movieId = screeningTable.getValueAt(selectedRow, 1).toString();
                String location = screeningTable.getValueAt(selectedRow, 2).toString();
                String startTimeStr = screeningTable.getValueAt(selectedRow, 3).toString();
                String endTimeStr = screeningTable.getValueAt(selectedRow, 4).toString();
                String availableSeatsStr = screeningTable.getValueAt(selectedRow, 5).toString();
                String ticketPriceStr = screeningTable.getValueAt(selectedRow, 6).toString();

                movieIdField.setText(movieId);
                locationField.setText(location);
                startTimeField.setText(startTimeStr);
                endTimeField.setText(endTimeStr);
                availableSeatsField.setText(availableSeatsStr);
                ticketPriceField.setText(ticketPriceStr);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to fill the form.");
            }
        }

        if (e.getSource() == updateButton) {
            int selectedRow = screeningTable.getSelectedRow();
            if (selectedRow != -1) {
                String screeningId = screeningTable.getValueAt(selectedRow, 0).toString();
                String movieId = movieIdField.getText().trim();
                String location = locationField.getText().trim();
                String startTimeStr = startTimeField.getText().trim();
                String endTimeStr = endTimeField.getText().trim();
                String availableSeatsStr = availableSeatsField.getText().trim();
                String ticketPriceStr = ticketPriceField.getText().trim();

                LocalDateTime startTime, endTime;
                int availableSeats;
                double ticketPrice;

                if(movieRepo.searchMovieByMovieId(movieId) == null) {
                    JOptionPane.showMessageDialog(this, "Movie with this ID Doesn't Exist", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    endTime = LocalDateTime.parse(endTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date-time format. Use YYYY-MM-DD HH:MM.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    availableSeats = Integer.parseInt(availableSeatsStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Available seats must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    ticketPrice = Double.parseDouble(ticketPriceStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ticket price must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Screening screening = new Screening();
                screening.setScreeningId(screeningId);
                screening.setMovieId(movieId);
                screening.setLocation(location);
                screening.setStartTime(startTime);
                screening.setEndTime(endTime);
                screening.setAvailableSeats(availableSeats);
                screening.setTicketPrice(ticketPrice);

                screeningRepo.updateScreening(screening);
                JOptionPane.showMessageDialog(this, "Screening updated successfully!");

                movieIdField.setText("");
                locationField.setText("");
                startTimeField.setText("");
                endTimeField.setText("");
                availableSeatsField.setText("");
                ticketPriceField.setText("");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
            }
        }
    }

    private void refreshTable() {
        String[] screeningColumns = {"Screening ID", "Movie ID", "Location", "Start Time", "End Time", "Available Seats", "Ticket Price"};
        Screening[] screenings = screeningRepo.getAllScreenings();
        String[][] screeningData = new String[screenings.length][7];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < screenings.length; i++) {
            if (screenings[i] != null) {
                screeningData[i][0] = screenings[i].getScreeningId();
                screeningData[i][1] = screenings[i].getMovieId();
                screeningData[i][2] = screenings[i].getLocation();
                screeningData[i][3] = screenings[i].getStartTime().format(formatter);
                screeningData[i][4] = screenings[i].getEndTime().format(formatter);
                screeningData[i][5] = String.valueOf(screenings[i].getAvailableSeats());
                screeningData[i][6] = String.valueOf(screenings[i].getTicketPrice());
            }
        }

        remove(screeningTableSP);

        screeningTable = new JTable(screeningData, screeningColumns);
        screeningTable.setBackground(new Color(0xb8b3fc));
        screeningTableSP = new JScrollPane(screeningTable);
        screeningTableSP.setBounds(30, 180, 720, 325);
        add(screeningTableSP);

        revalidate();
        repaint();
    }
}
