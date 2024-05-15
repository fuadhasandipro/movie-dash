package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends  JPanel  {

	private JTextField searchField;
	private JLabel headerTitle;
	private JLabel searchLabel;
	private JButton searchButton;
	private JPanel moviesPanel;
	private JLabel mLabel;
	private JLabel mTotal;
	private JButton mViewAll;
	private JPanel genresPanel;
	private JLabel gLabel;
	private JLabel gTotal;
	private JButton gViewAll;
	private JPanel actorPanel;
	private JLabel aLabel;
	private JLabel aTotal;
	private JButton aViewAll;
	private JPanel directorPanel;
	private JLabel dLabel;
	private JLabel dTotal;
	private JButton dViewAll;
	private JPanel screensPanel;
	private JLabel tsLabel;
	private JLabel tsTotal;
	private JButton tsViewAll;
	private JPanel reservationsPanel;
	private JLabel reservationlabel;
	private JLabel reservationTotal;
	private JButton reservationViewAll;
	private JPanel transactionPanel;
	private JLabel tLabelh;
	private JLabel tTotal;
	private JButton tViewAll;
	private JPanel reviewsPanel;
	private JLabel reviewLabel;
	private JLabel reviewTotal;
	private JButton reviewViewAll;
	private JButton addNewMovie;
	private JLabel statisticsLabel;

    public AdminDashboard() {

		searchField = new JTextField();
		headerTitle = new JLabel();
		searchLabel = new JLabel();
		searchButton = new JButton();
		moviesPanel = new JPanel();
		mLabel = new JLabel();
		mTotal = new JLabel();
		mViewAll = new JButton();
		genresPanel = new JPanel();
		gLabel = new JLabel();
		gTotal = new JLabel();
		gViewAll = new JButton();
		actorPanel = new JPanel();
		aLabel = new JLabel();
		aTotal = new JLabel();
		aViewAll = new JButton();
		directorPanel = new JPanel();
		dLabel = new JLabel();
		dTotal = new JLabel();
		dViewAll = new JButton();
		screensPanel = new JPanel();
		tsLabel = new JLabel();
		tsTotal = new JLabel();
		tsViewAll = new JButton();
		reservationsPanel = new JPanel();
		reservationlabel = new JLabel();
		reservationTotal = new JLabel();
		reservationViewAll = new JButton();
		transactionPanel = new JPanel();
		tLabelh = new JLabel();
		tTotal = new JLabel();
		tViewAll = new JButton();
		reviewsPanel = new JPanel();
		reviewLabel = new JLabel();
		reviewTotal = new JLabel();
		reviewViewAll = new JButton();
		addNewMovie = new JButton();
		statisticsLabel = new JLabel();

		// RIGHT PANEL

        setBackground(new Color(0x201f2d));
        setLayout(null);

        //---- searchField ----
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        add(searchField);
        searchField.setBounds(260, 45, 405, 25);

        //---- headerTitle ----
        headerTitle.setText("Dashboard");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", headerTitle.getFont().getStyle() | Font.BOLD, headerTitle.getFont().getSize() + 12));
        add(headerTitle);
			headerTitle.setBounds(30, 40, 230, headerTitle.getPreferredSize().height);

        //---- searchLabel ----
        searchLabel.setText("Enter Movie ID to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        add(searchLabel);
        searchLabel.setBounds(new Rectangle(new Point(260, 25), searchLabel.getPreferredSize()));

        //---- searchButton ----
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton.setIcon(searchIcon);
        add(searchButton);
        searchButton.setBounds(670, 45, 30, 25);

        //======== moviesPanel ========
        {
            moviesPanel.setBackground(new Color(0x333147));
            moviesPanel.setLayout(null);

            //---- mLabel ----
            mLabel.setText("Unique Movies");
            mLabel.setForeground(new Color(0xb8b3fc));
            mLabel.setFont(new Font("Verdana", mLabel.getFont().getStyle() | Font.BOLD, mLabel.getFont().getSize() + 2));
            moviesPanel.add(mLabel);
            mLabel.setBounds(10, 10, 130, 20);

            //---- mTotal ----
            mTotal.setText("5678  ");
            mTotal.setForeground(Color.white);
            mTotal.setFont(new Font("Verdana", mTotal.getFont().getStyle() | Font.BOLD, mTotal.getFont().getSize() + 14));
            moviesPanel.add(mTotal);
            mTotal.setBounds(10, 40, 130, 20);

            //---- mViewAll ----
            mViewAll.setText("View All");
            mViewAll.setBackground(new Color(0x201f2d));
            mViewAll.setForeground(Color.white);
            moviesPanel.add(mViewAll);
            mViewAll.setBounds(new Rectangle(new Point(10, 75), mViewAll.getPreferredSize()));
        }
        add(moviesPanel);
        moviesPanel.setBounds(35, 205, 150, 110);

        //======== genresPanel ========
        {
            genresPanel.setBackground(new Color(0x333147));
            genresPanel.setLayout(null);

            //---- gLabel ----
            gLabel.setText("Genres Listed");
            gLabel.setForeground(new Color(0xb8b3fc));
            gLabel.setFont(new Font("Verdana", gLabel.getFont().getStyle() | Font.BOLD, gLabel.getFont().getSize() + 2));
            genresPanel.add(gLabel);
            gLabel.setBounds(10, 10, 130, 20);

            //---- gTotal ----
            gTotal.setText("5678  ");
            gTotal.setForeground(Color.white);
            gTotal.setFont(new Font("Verdana", gTotal.getFont().getStyle() | Font.BOLD, gTotal.getFont().getSize() + 14));
            genresPanel.add(gTotal);
            gTotal.setBounds(10, 40, 130, 20);

            //---- gViewAll ----
            gViewAll.setText("View All");
            gViewAll.setBackground(new Color(0x201f2d));
            gViewAll.setForeground(Color.white);
            genresPanel.add(gViewAll);
            gViewAll.setBounds(new Rectangle(new Point(10, 75), gViewAll.getPreferredSize()));
        }
        add(genresPanel);
        genresPanel.setBounds(200, 205, 150, 110);

        //======== actorPanel ========
        {
            actorPanel.setBackground(new Color(0x333147));
            actorPanel.setLayout(null);

            //---- aLabel ----
            aLabel.setText("Actors Listed");
            aLabel.setForeground(new Color(0xb8b3fc));
            aLabel.setFont(new Font("Verdana", aLabel.getFont().getStyle() | Font.BOLD, aLabel.getFont().getSize() + 2));
            actorPanel.add(aLabel);
            aLabel.setBounds(10, 10, 130, 20);

            //---- aTotal ----
            aTotal.setText("5678  ");
            aTotal.setForeground(Color.white);
            aTotal.setFont(new Font("Verdana", aTotal.getFont().getStyle() | Font.BOLD, aTotal.getFont().getSize() + 14));
            actorPanel.add(aTotal);
            aTotal.setBounds(10, 40, 130, 20);

            //---- aViewAll ----
            aViewAll.setText("View All");
            aViewAll.setBackground(new Color(0x201f2d));
            aViewAll.setForeground(Color.white);
            actorPanel.add(aViewAll);
            aViewAll.setBounds(new Rectangle(new Point(10, 75), aViewAll.getPreferredSize()));
        }
        add(actorPanel);
        actorPanel.setBounds(365, 205, 150, 110);

        //======== directorPanel ========
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
            dTotal.setText("5678  ");
            dTotal.setForeground(Color.white);
            dTotal.setFont(new Font("Verdana", dTotal.getFont().getStyle() | Font.BOLD, dTotal.getFont().getSize() + 14));
            directorPanel.add(dTotal);
            dTotal.setBounds(10, 40, 130, 20);

            //---- dViewAll ----
            dViewAll.setText("View All");
            dViewAll.setBackground(new Color(0x201f2d));
            dViewAll.setForeground(Color.white);
            directorPanel.add(dViewAll);
            dViewAll.setBounds(new Rectangle(new Point(10, 75), dViewAll.getPreferredSize()));
        }
        add(directorPanel);
        directorPanel.setBounds(530, 205, 150, 110);

        //======== screensPanel ========
        {
            screensPanel.setBackground(new Color(0x333147));
            screensPanel.setLayout(null);

            //---- tsLabel ----
            tsLabel.setText("Total Screens");
            tsLabel.setForeground(new Color(0xb8b3fc));
            tsLabel.setFont(new Font("Verdana", tsLabel.getFont().getStyle() | Font.BOLD, tsLabel.getFont().getSize() + 2));
            screensPanel.add(tsLabel);
            tsLabel.setBounds(10, 10, 130, 20);

            //---- tsTotal ----
            tsTotal.setText("5678  ");
            tsTotal.setForeground(Color.white);
            tsTotal.setFont(new Font("Verdana", tsTotal.getFont().getStyle() | Font.BOLD, tsTotal.getFont().getSize() + 14));
            screensPanel.add(tsTotal);
            tsTotal.setBounds(10, 40, 130, 20);

            //---- tsViewAll ----
            tsViewAll.setText("View All");
            tsViewAll.setBackground(new Color(0x201f2d));
            tsViewAll.setForeground(Color.white);
            screensPanel.add(tsViewAll);
            tsViewAll.setBounds(new Rectangle(new Point(10, 75), tsViewAll.getPreferredSize()));
        }
        add(screensPanel);
        screensPanel.setBounds(35, 330, 150, 110);

        //======== reservationsPanel ========
        {
            reservationsPanel.setBackground(new Color(0x333147));
            reservationsPanel.setLayout(null);

            //---- reservationlabel ----
            reservationlabel.setText("Reservations");
            reservationlabel.setForeground(new Color(0xb8b3fc));
            reservationlabel.setFont(new Font("Verdana", reservationlabel.getFont().getStyle() | Font.BOLD, reservationlabel.getFont().getSize() + 2));
            reservationsPanel.add(reservationlabel);
            reservationlabel.setBounds(10, 10, 130, 20);

            //---- reservationTotal ----
            reservationTotal.setText("5678  ");
            reservationTotal.setForeground(Color.white);
            reservationTotal.setFont(new Font("Verdana", reservationTotal.getFont().getStyle() | Font.BOLD, reservationTotal.getFont().getSize() + 14));
            reservationsPanel.add(reservationTotal);
            reservationTotal.setBounds(10, 40, 130, 20);

            //---- reservationViewAll ----
            reservationViewAll.setText("View All");
            reservationViewAll.setBackground(new Color(0x201f2d));
            reservationViewAll.setForeground(Color.white);
            reservationsPanel.add(reservationViewAll);
            reservationViewAll.setBounds(new Rectangle(new Point(10, 75), reservationViewAll.getPreferredSize()));
        }
        add(reservationsPanel);
        reservationsPanel.setBounds(200, 330, 150, 110);

        //======== transactionPanel ========
        {
            transactionPanel.setBackground(new Color(0x333147));
            transactionPanel.setLayout(null);

            //---- tLabelh ----
            tLabelh.setText("Transactions");
            tLabelh.setForeground(new Color(0xb8b3fc));
            tLabelh.setFont(new Font("Verdana", tLabelh.getFont().getStyle() | Font.BOLD, tLabelh.getFont().getSize() + 2));
            transactionPanel.add(tLabelh);
            tLabelh.setBounds(10, 10, 130, 20);

            //---- tTotal ----
            tTotal.setText("$5678 ");
            tTotal.setForeground(Color.white);
            tTotal.setFont(new Font("Verdana", tTotal.getFont().getStyle() | Font.BOLD, tTotal.getFont().getSize() + 14));
            transactionPanel.add(tTotal);
            tTotal.setBounds(10, 40, 130, 20);

            //---- tViewAll ----
            tViewAll.setText("View All");
            tViewAll.setBackground(new Color(0x201f2d));
            tViewAll.setForeground(Color.white);
            transactionPanel.add(tViewAll);
            tViewAll.setBounds(new Rectangle(new Point(10, 75), tViewAll.getPreferredSize()));
        }
        add(transactionPanel);
        transactionPanel.setBounds(365, 330, 150, 110);

        //======== reviewsPanel ========
        {
            reviewsPanel.setBackground(new Color(0x333147));
            reviewsPanel.setLayout(null);

            //---- reviewLabel ----
            reviewLabel.setText("Reviews Total");
            reviewLabel.setForeground(new Color(0xb8b3fc));
            reviewLabel.setFont(new Font("Verdana", reviewLabel.getFont().getStyle() | Font.BOLD, reviewLabel.getFont().getSize() + 2));
            reviewsPanel.add(reviewLabel);
            reviewLabel.setBounds(10, 10, 130, 20);

            //---- reviewTotal ----
            reviewTotal.setText("4234");
            reviewTotal.setForeground(Color.white);
            reviewTotal.setFont(new Font("Verdana", reviewTotal.getFont().getStyle() | Font.BOLD, reviewTotal.getFont().getSize() + 14));
            reviewsPanel.add(reviewTotal);
            reviewTotal.setBounds(10, 40, 130, 20);

            //---- reviewViewAll ----
            reviewViewAll.setText("View All");
            reviewViewAll.setBackground(new Color(0x201f2d));
            reviewViewAll.setForeground(Color.white);
            reviewsPanel.add(reviewViewAll);
            reviewViewAll.setBounds(new Rectangle(new Point(10, 75), reviewViewAll.getPreferredSize()));
        }
        add(reviewsPanel);
        reviewsPanel.setBounds(530, 330, 150, 110);

        //---- addNewMovie ----
        addNewMovie.setText("Add New Movie");
        addNewMovie.setBackground(new Color(0x201f2d));
        addNewMovie.setForeground(Color.white);
        addNewMovie.setFont(new Font("Verdana", addNewMovie.getFont().getStyle() | Font.BOLD, addNewMovie.getFont().getSize() + 2));
        add(addNewMovie);
        addNewMovie.setBounds(485, 150, 195, 35);

        //---- statisticsLabel ----
        statisticsLabel.setText("Statistics");
        statisticsLabel.setForeground(Color.white);
        statisticsLabel.setFont(new Font("Verdana", statisticsLabel.getFont().getStyle() | Font.BOLD, statisticsLabel.getFont().getSize() + 10));
        add(statisticsLabel);
        statisticsLabel.setBounds(35, 150, 160, 33);
    }

}