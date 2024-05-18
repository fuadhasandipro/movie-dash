package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entities.*;

public class AdminView extends JFrame implements ActionListener {
    
    private JPanel leftPanel;
    private JLabel logo;
	private JButton dashboardButton;
	private JButton moviesButton;
	private JButton genresButton;
	private JButton screensButton;
	private JButton reservationButton;
	private JButton paymentsButton;
	private JButton usersButton;
	private JButton profileLink;
	private JButton logoutButton;
	private JButton reviewsButton;
	private JPanel rightPanelContainer;
    private CardLayout cardLayout;
    private AdminDashboard adminDashboardPanel;
    private MoviesPanel adminMoviePanel;
    private GenresPanel adminGenrePanel;
    private ScreeningPanel adminScreeningPanel;
    private ReservationPanel adminReservationPanel;
    private PaymentPanel adminPaymentPanel;
    private ReviewPanel adminReviewPanel;
    private UserListPanel adminUserListPanel;
    private CustomerProfilePanel adminProfilePanel;
    private User currentUser;

    public AdminView(User currentUser) {
        super("Admin Dashboard");
        this.currentUser = currentUser;

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

		leftPanel = new JPanel();
        logo = new JLabel();
		dashboardButton = new JButton();
		moviesButton = new JButton();
		genresButton = new JButton();
		screensButton = new JButton();
		reservationButton = new JButton();
		paymentsButton = new JButton();
		usersButton = new JButton();
		profileLink = new JButton();
		logoutButton = new JButton();
		reviewsButton = new JButton();
        rightPanelContainer = new JPanel();
        cardLayout = new CardLayout();
        adminDashboardPanel = new AdminDashboard();
        adminMoviePanel = new MoviesPanel();
        adminGenrePanel = new GenresPanel();
        adminScreeningPanel = new ScreeningPanel();
        adminReservationPanel = new ReservationPanel();
        adminPaymentPanel = new PaymentPanel();
        adminReviewPanel = new ReviewPanel();
        adminUserListPanel = new UserListPanel();
        adminProfilePanel = new CustomerProfilePanel(this.currentUser);
        
        rightPanelContainer.setLayout(cardLayout);

        // LEFT PANEL
        leftPanel.setBackground(new Color(0x333147));
        leftPanel.setLayout(null);

        //---- logo ----
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("../assets/img/logo.png"));

        Image scaledLogoImage = logoIcon.getImage().getScaledInstance(95, 80, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogoImage);

        logo.setIcon(scaledLogoIcon);
        logo.setForeground(Color.white);
        logo.setFont(logo.getFont().deriveFont(logo.getFont().getStyle() | Font.ITALIC, logo.getFont().getSize() + 14f));
        leftPanel.add(logo);
        logo.setBounds(15, 25, 95, 80);

        //---- dashboardButton ----
        dashboardButton.setText("Dashboard");
        dashboardButton.setBackground(new Color(0x1c1b30));
        dashboardButton.setForeground(Color.white);
        dashboardButton.setFont(new Font("Verdana", Font.BOLD, 14));
        leftPanel.add(dashboardButton);
        dashboardButton.addActionListener(this);
        dashboardButton.setBounds(15, 120, 150, 30);

        //---- moviesButton ----
        moviesButton.setText("Movies");
        moviesButton.setBackground(new Color(0x1c1b30));
        moviesButton.setForeground(Color.white);
        moviesButton.setFont(new Font("Verdana", Font.BOLD, 14));
        moviesButton.addActionListener(this);
        leftPanel.add(moviesButton);
        moviesButton.setBounds(15, 155, 150, 30);

        //---- genresButton ----
        genresButton.setText("Genres");
        genresButton.setBackground(new Color(0x1c1b30));
        genresButton.setForeground(Color.white);
        genresButton.setFont(new Font("Verdana", Font.BOLD, 14));
        genresButton.addActionListener(this);
        leftPanel.add(genresButton);
        genresButton.setBounds(15, 190, 150, 30);

        //---- screensButton ----
        screensButton.setText("Screens");
        screensButton.setBackground(new Color(0x1c1b30));
        screensButton.setForeground(Color.white);
        screensButton.setFont(new Font("Verdana", Font.BOLD, 14));
        screensButton.addActionListener(this);
        leftPanel.add(screensButton);
        screensButton.setBounds(15, 225, 150, 30);

        //---- reservationButton ----
        reservationButton.setText("Reservations");
        reservationButton.setBackground(new Color(0x1c1b30));
        reservationButton.setForeground(Color.white);
        reservationButton.setFont(new Font("Verdana", Font.BOLD, 14));
        reservationButton.addActionListener(this);
        leftPanel.add(reservationButton);
        reservationButton.setBounds(15, 260, 150, 30);

        //---- paymentsButton ----
        paymentsButton.setText("Payments");
        paymentsButton.setBackground(new Color(0x1c1b30));
        paymentsButton.setForeground(Color.white);
        paymentsButton.setFont(new Font("Verdana", Font.BOLD, 14));
        paymentsButton.addActionListener(this);
        leftPanel.add(paymentsButton);
        paymentsButton.setBounds(15, 295, 150, 30);

                //---- reviewsButton ----
        reviewsButton.setText("Reviews");
        reviewsButton.setBackground(new Color(0x1c1b30));
        reviewsButton.setForeground(Color.white);
        reviewsButton.setFont(new Font("Verdana", Font.BOLD, 14));
        reviewsButton.addActionListener(this);
        leftPanel.add(reviewsButton);
        reviewsButton.setBounds(15, 330, 150, 30);

        //---- usersButton ----
        usersButton.setText("Users");
        usersButton.setBackground(new Color(0x1c1b30));
        usersButton.setForeground(Color.white);
        usersButton.setFont(new Font("Verdana", Font.BOLD, 14));
        usersButton.addActionListener(this);
        leftPanel.add(usersButton);
        usersButton.setBounds(15, 365, 150, 30);

        //---- profileLink ----
        profileLink.setText("Profile");
        profileLink.setBackground(new Color(0x1c1b30));
        profileLink.setForeground(Color.white);
        profileLink.setFont(new Font("Verdana", Font.BOLD, 14));
        profileLink.addActionListener(this);
        leftPanel.add(profileLink);
        profileLink.setBounds(15, 400, 150, 30);

        //---- logoutButton ----
        logoutButton.setText("Logout");
        logoutButton.setBackground(new Color(0x666666));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("Verdana", Font.BOLD, 14));
        logoutButton.addActionListener(this);
        leftPanel.add(logoutButton);
        logoutButton.setBounds(15, 435, 150, 30);

        contentPane.add(leftPanel);
        leftPanel.setBounds(0, 0, 180, 570);

        contentPane.add(rightPanelContainer);
        rightPanelContainer.setBounds(180, 0, 720, 570);

        rightPanelContainer.add(adminDashboardPanel, "dashboard");
        rightPanelContainer.add(adminMoviePanel, "movies");
        rightPanelContainer.add(adminGenrePanel, "genres");
        rightPanelContainer.add(adminScreeningPanel, "screenings");
        rightPanelContainer.add(adminReservationPanel, "reservations");
        rightPanelContainer.add(adminPaymentPanel, "payments");
        rightPanelContainer.add(adminReviewPanel, "reviews");
        rightPanelContainer.add(adminUserListPanel, "users");
        rightPanelContainer.add(adminProfilePanel, "profile");

        cardLayout.show(rightPanelContainer, "dashboard");

        contentPane.setPreferredSize(new Dimension(900, 600));
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
        
        switch (command) {
            case "Dashboard":
                cardLayout.show(rightPanelContainer, "dashboard");
                break;
            case "Movies":
                cardLayout.show(rightPanelContainer, "movies");
                break;
            case "Genres":
                cardLayout.show(rightPanelContainer, "genres");
                break;
            case "Screens":
                cardLayout.show(rightPanelContainer, "screenings");
                break;
            case "Reservations":
                cardLayout.show(rightPanelContainer, "reservations");
                break;
            case "Payments":
                cardLayout.show(rightPanelContainer, "payments");
                break;
            case "Reviews":
                cardLayout.show(rightPanelContainer, "reviews");
                break;
            case "Users":
                cardLayout.show(rightPanelContainer, "users");
                break;
            case "Profile":
                cardLayout.show(rightPanelContainer, "profile");
                break;
        }
	}

}
