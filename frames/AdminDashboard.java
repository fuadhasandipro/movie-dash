package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.*;
import repositories.*;
import java.text.DecimalFormat;

public class AdminDashboard extends  JPanel  {

	private JTextField searchField;
	private JLabel headerTitle;
	private JLabel searchLabel;
	private JButton searchButton;
	private JPanel moviesPanel;
	private JLabel mLabel;
	private JLabel mTotal;

	private JPanel genresPanel;
	private JLabel gLabel;
	private JLabel gTotal;

	private JPanel actorPanel;
	private JLabel aLabel;
	private JLabel aTotal;

	private JPanel directorPanel;
	private JLabel dLabel;
	private JLabel dTotal;

	private JPanel screensPanel;
	private JLabel tsLabel;
	private JLabel tsTotal;

	private JPanel reservationsPanel;
	private JLabel reservationlabel;
	private JLabel reservationTotal;

	private JPanel transactionPanel;
	private JLabel tLabelh;
	private JLabel tTotal;

	private JPanel reviewsPanel;
	private JLabel reviewLabel;
	private JLabel reviewTotal;

	private JLabel statisticsLabel;

    public AdminDashboard() {

		searchField = new JTextField();
		headerTitle = new JLabel();
		searchLabel = new JLabel();
		searchButton = new JButton();
		moviesPanel = new JPanel();
		mLabel = new JLabel();
		mTotal = new JLabel();

		genresPanel = new JPanel();
		gLabel = new JLabel();
		gTotal = new JLabel();

		actorPanel = new JPanel();
		aLabel = new JLabel();
		aTotal = new JLabel();

		directorPanel = new JPanel();
		dLabel = new JLabel();
		dTotal = new JLabel();

		screensPanel = new JPanel();
		tsLabel = new JLabel();
		tsTotal = new JLabel();

		reservationsPanel = new JPanel();
		reservationlabel = new JLabel();
		reservationTotal = new JLabel();

		transactionPanel = new JPanel();
		tLabelh = new JLabel();
		tTotal = new JLabel();

		reviewsPanel = new JPanel();
		reviewLabel = new JLabel();
		reviewTotal = new JLabel();

		statisticsLabel = new JLabel();

        setBackground(new Color(0x201f2d));
        setLayout(null);

        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        add(searchField);
        searchField.setBounds(260, 45, 405, 25);

        headerTitle.setText("Dashboard");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", headerTitle.getFont().getStyle() | Font.BOLD, headerTitle.getFont().getSize() + 12));
        add(headerTitle);
			headerTitle.setBounds(30, 40, 230, headerTitle.getPreferredSize().height);

        searchLabel.setText("Enter Movie ID to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        add(searchLabel);
        searchLabel.setBounds(new Rectangle(new Point(260, 25), searchLabel.getPreferredSize()));

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton.setIcon(searchIcon);
        add(searchButton);
        searchButton.setBounds(670, 45, 30, 25);

        {
            moviesPanel.setBackground(new Color(0x333147));
            moviesPanel.setLayout(null);

            mLabel.setText("Unique Movies");
            mLabel.setForeground(new Color(0xb8b3fc));
            mLabel.setFont(new Font("Verdana", mLabel.getFont().getStyle() | Font.BOLD, mLabel.getFont().getSize() + 2));
            moviesPanel.add(mLabel);
            mLabel.setBounds(10, 10, 130, 20);


            MovieRepo mrp = new MovieRepo();
            int totalMovies = 0;

            for (Movie movie : mrp.getAllMovies()) {
                if (movie != null) {
                    totalMovies++;
                }
            }

            mTotal.setText(String.valueOf(totalMovies));
            mTotal.setForeground(Color.white);
            mTotal.setFont(new Font("Verdana", mTotal.getFont().getStyle() | Font.BOLD, mTotal.getFont().getSize() + 14));
            moviesPanel.add(mTotal);
            mTotal.setBounds(10, 40, 130, 20);
        }
        add(moviesPanel);
        moviesPanel.setBounds(35, 205, 150, 110);

        {
            genresPanel.setBackground(new Color(0x333147));
            genresPanel.setLayout(null);

            gLabel.setText("Genres Listed");
            gLabel.setForeground(new Color(0xb8b3fc));
            gLabel.setFont(new Font("Verdana", gLabel.getFont().getStyle() | Font.BOLD, gLabel.getFont().getSize() + 2));
            genresPanel.add(gLabel);
            gLabel.setBounds(10, 10, 130, 20);

            GenreRepo gerp = new GenreRepo();
            int totalGenres = 0;

            for (Genre genre : gerp.getAllGenres()) {
                if (genre != null) {
                    totalGenres++;
                }
            }

            gTotal.setText(String.valueOf(totalGenres));
            gTotal.setForeground(Color.white);
            gTotal.setFont(new Font("Verdana", gTotal.getFont().getStyle() | Font.BOLD, gTotal.getFont().getSize() + 14));
            genresPanel.add(gTotal);
            gTotal.setBounds(10, 40, 130, 20);
        }
        add(genresPanel);
        genresPanel.setBounds(200, 205, 150, 110);

        {
            actorPanel.setBackground(new Color(0x333147));
            actorPanel.setLayout(null);

            aLabel.setText("Actors Listed");
            aLabel.setForeground(new Color(0xb8b3fc));
            aLabel.setFont(new Font("Verdana", aLabel.getFont().getStyle() | Font.BOLD, aLabel.getFont().getSize() + 2));
            actorPanel.add(aLabel);
            aLabel.setBounds(10, 10, 130, 20);

            aTotal.setText("12");
            aTotal.setForeground(Color.white);
            aTotal.setFont(new Font("Verdana", aTotal.getFont().getStyle() | Font.BOLD, aTotal.getFont().getSize() + 14));
            actorPanel.add(aTotal);
            aTotal.setBounds(10, 40, 130, 20);

        }
        add(actorPanel);
        actorPanel.setBounds(365, 205, 150, 110);

        {
            directorPanel.setBackground(new Color(0x333147));
            directorPanel.setLayout(null);

            //---- dLabel ----
            dLabel.setText("Directors Listed");
            dLabel.setForeground(new Color(0xb8b3fc));
            dLabel.setFont(new Font("Verdana", dLabel.getFont().getStyle() | Font.BOLD, dLabel.getFont().getSize() + 2));
            directorPanel.add(dLabel);
            dLabel.setBounds(10, 10, 130, 20);

            //---- dTotal ----
            dTotal.setText("7");
            dTotal.setForeground(Color.white);
            dTotal.setFont(new Font("Verdana", dTotal.getFont().getStyle() | Font.BOLD, dTotal.getFont().getSize() + 14));
            directorPanel.add(dTotal);
            dTotal.setBounds(10, 40, 130, 20);
        }
        add(directorPanel);
        directorPanel.setBounds(530, 205, 150, 110);

        {
            screensPanel.setBackground(new Color(0x333147));
            screensPanel.setLayout(null);

            tsLabel.setText("Total Screens");
            tsLabel.setForeground(new Color(0xb8b3fc));
            tsLabel.setFont(new Font("Verdana", tsLabel.getFont().getStyle() | Font.BOLD, tsLabel.getFont().getSize() + 2));
            screensPanel.add(tsLabel);
            tsLabel.setBounds(10, 10, 130, 20);

            ScreeningRepo srp = new ScreeningRepo();
            int totalScreenings = 0;

            for (Screening screening : srp.getAllScreenings()) {
                if (screening != null) {
                    totalScreenings++;
                }
            }

            tsTotal.setText(String.valueOf(totalScreenings));
            tsTotal.setForeground(Color.white);
            tsTotal.setFont(new Font("Verdana", tsTotal.getFont().getStyle() | Font.BOLD, tsTotal.getFont().getSize() + 14));
            screensPanel.add(tsTotal);
            tsTotal.setBounds(10, 40, 130, 20);
        }
        add(screensPanel);
        screensPanel.setBounds(35, 330, 150, 110);

        {
            reservationsPanel.setBackground(new Color(0x333147));
            reservationsPanel.setLayout(null);

            reservationlabel.setText("Reservations");
            reservationlabel.setForeground(new Color(0xb8b3fc));
            reservationlabel.setFont(new Font("Verdana", reservationlabel.getFont().getStyle() | Font.BOLD, reservationlabel.getFont().getSize() + 2));
            reservationsPanel.add(reservationlabel);
            reservationlabel.setBounds(10, 10, 130, 20);

            ReservationRepo rrp = new ReservationRepo();
            int totalReservations = 0;

            for (Reservation reservation : rrp.getAllReservations()) {
                if (reservation != null) {
                    totalReservations++;
                }
            }

            reservationTotal.setText(String.valueOf(totalReservations));
            reservationTotal.setForeground(Color.white);
            reservationTotal.setFont(new Font("Verdana", reservationTotal.getFont().getStyle() | Font.BOLD, reservationTotal.getFont().getSize() + 14));
            reservationsPanel.add(reservationTotal);
            reservationTotal.setBounds(10, 40, 130, 20);

        }
        add(reservationsPanel);
        reservationsPanel.setBounds(200, 330, 150, 110);

        {
            transactionPanel.setBackground(new Color(0x333147));
            transactionPanel.setLayout(null);

            tLabelh.setText("Transactions");
            tLabelh.setForeground(new Color(0xb8b3fc));
            tLabelh.setFont(new Font("Verdana", tLabelh.getFont().getStyle() | Font.BOLD, tLabelh.getFont().getSize() + 2));
            transactionPanel.add(tLabelh);
            tLabelh.setBounds(10, 10, 130, 20);

            PaymentRepo prp = new PaymentRepo();
            double totalAmount = 0.0;

            for (Payment payment : prp.getAllPayments()) {
                if (payment != null) {
                    totalAmount += payment.getAmount();
                }
            }

            DecimalFormat df = new DecimalFormat("#.##");

            tTotal.setText("$"+ String.valueOf(df.format(totalAmount)));
            tTotal.setForeground(Color.white);
            tTotal.setFont(new Font("Verdana", tTotal.getFont().getStyle() | Font.BOLD, tTotal.getFont().getSize() + 3));
            transactionPanel.add(tTotal);
            tTotal.setBounds(10, 40, 130, 20);
        }
        add(transactionPanel);
        transactionPanel.setBounds(365, 330, 150, 110);

        {
            reviewsPanel.setBackground(new Color(0x333147));
            reviewsPanel.setLayout(null);

            reviewLabel.setText("Ratings Total");
            reviewLabel.setForeground(new Color(0xb8b3fc));
            reviewLabel.setFont(new Font("Verdana", reviewLabel.getFont().getStyle() | Font.BOLD, reviewLabel.getFont().getSize() + 2));
            reviewsPanel.add(reviewLabel);
            reviewLabel.setBounds(10, 10, 130, 20);

            ReviewRepo reviewRepo = new ReviewRepo();
            int totalReviews = 0;

            for (Review review : reviewRepo.getAllReviews()) {
                if (review != null) {
                    totalReviews++;
                }
            }

            reviewTotal.setText(String.valueOf(totalReviews));
            reviewTotal.setForeground(Color.white);
            reviewTotal.setFont(new Font("Verdana", reviewTotal.getFont().getStyle() | Font.BOLD, reviewTotal.getFont().getSize() + 14));
            reviewsPanel.add(reviewTotal);
            reviewTotal.setBounds(10, 40, 130, 20);
        }
        add(reviewsPanel);
        reviewsPanel.setBounds(530, 330, 150, 110);

        statisticsLabel.setText("Statistics");
        statisticsLabel.setForeground(Color.white);
        statisticsLabel.setFont(new Font("Verdana", statisticsLabel.getFont().getStyle() | Font.BOLD, statisticsLabel.getFont().getSize() + 10));
        add(statisticsLabel);
        statisticsLabel.setBounds(35, 150, 160, 33);
    }
}