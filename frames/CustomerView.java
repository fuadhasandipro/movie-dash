package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.*;

public class CustomerView extends JFrame implements ActionListener {
    
    private User currentUser;
    private JPanel leftPanel;
    private JLabel logo;
	private JButton dashboardButton;
	private JButton moviesButton;
	private JButton genresButton;
	private JButton actorsButton;
	private JButton directorsButton;
	private JButton screensButton;
	private JButton reservationButton;
	private JButton paymentsButton;
	private JButton usersButton;
	private JLabel label1;
	private JButton profileLink;
	private JButton logoutButton;
	private JButton reloadButton;
	private JButton reviewsButton;
	private JPanel rightPanelContainer;
    private CardLayout cardLayout;
    private CustomerMoviesPanel customerMoviesPanel;
    private CustomerReservationPanel customerReservationPanel;
    private CustomerProfilePanel customerProfilePanel;
    private CustomerRatingsPanel customerRatingsPanel;
    private CustomerPaymentsPanel customerPaymentsPanel;

    public CustomerView(User currentUser) {
        super("Customer Dashboard");

        this.currentUser = currentUser;

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

		leftPanel = new JPanel();
        logo = new JLabel();
		dashboardButton = new JButton();
		moviesButton = new JButton();
		genresButton = new JButton();
		actorsButton = new JButton();
		directorsButton = new JButton();
		screensButton = new JButton();
		reservationButton = new JButton();
		paymentsButton = new JButton();
		usersButton = new JButton();
		label1 = new JLabel();
		profileLink = new JButton();
		logoutButton = new JButton();
		reloadButton = new JButton();
		reviewsButton = new JButton();
        rightPanelContainer = new JPanel();
        cardLayout = new CardLayout();
        customerMoviesPanel = new CustomerMoviesPanel(this.currentUser, this);
        customerReservationPanel = new CustomerReservationPanel(this.currentUser, this);
        customerProfilePanel = new CustomerProfilePanel(this.currentUser);
        customerRatingsPanel = new CustomerRatingsPanel(this.currentUser);
        customerPaymentsPanel = new CustomerPaymentsPanel(this.currentUser);
        
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

        //---- moviesButton ----
        moviesButton.setText("All Movies");
        moviesButton.setBackground(new Color(0x1c1b30));
        moviesButton.setForeground(Color.white);
        moviesButton.setFont(new Font("Verdana", Font.BOLD, 14));
        moviesButton.addActionListener(this);
        leftPanel.add(moviesButton);
        moviesButton.setBounds(15, 120, 150, 30);

        //---- reservationButton ----
        reservationButton.setText("Your Reservations");
        reservationButton.setBackground(new Color(0x1c1b30));
        reservationButton.setForeground(Color.white);
        reservationButton.setFont(new Font("Verdana", Font.BOLD, 14));
        reservationButton.addActionListener(this);
        leftPanel.add(reservationButton);
        reservationButton.setBounds(15, 155, 150, 30);

        //---- payments ----
        genresButton.setText("Your Payments");
        genresButton.setBackground(new Color(0x1c1b30));
        genresButton.setForeground(Color.white);
        genresButton.setFont(new Font("Verdana", Font.BOLD, 14));
        genresButton.addActionListener(this);
        leftPanel.add(genresButton);
        genresButton.setBounds(15, 190, 150, 30);

        //---- reviews ----
        actorsButton.setText("Your Reviews");
        actorsButton.setBackground(new Color(0x1c1b30));
        actorsButton.setForeground(Color.white);
        actorsButton.setFont(new Font("Verdana", Font.BOLD, 14));
        actorsButton.addActionListener(this);
        leftPanel.add(actorsButton);
        actorsButton.setBounds(15, 225, 150, 30);

        //---- label1 ----
        ImageIcon ppImage = new ImageIcon(getClass().getResource("../assets/img/dashboard/profile.png"));
        label1.setIcon(ppImage);
        label1.setForeground(Color.white);
        leftPanel.add(label1);
        label1.setBounds(40, 265, 30, 20);

        //---- profileLink ----
        profileLink.setText("    Profile");
        profileLink.setBackground(new Color(0x1c1b30));
        profileLink.setForeground(Color.white);
        profileLink.setFont(new Font("Verdana", Font.BOLD, 14));
        profileLink.addActionListener(this);
        leftPanel.add(profileLink);
        profileLink.setBounds(15, 260, 150, 30);

        //---- reloadbutton ----
        reloadButton.setText("Reload Data");
        reloadButton.setBackground(new Color(0x1c1b30));
        reloadButton.setForeground(Color.white);
        reloadButton.setFont(new Font("Verdana", Font.BOLD, 14));
        reloadButton.addActionListener(this);
        leftPanel.add(reloadButton);
        reloadButton.setBounds(15, 295, 150, 30);

        //---- logoutButton ----
        logoutButton.setText("Logout");
        logoutButton.setBackground(new Color(0x666666));
        logoutButton.setForeground(Color.white);
        logoutButton.setFont(new Font("Verdana", Font.BOLD, 14));
        leftPanel.add(logoutButton);
        logoutButton.setBounds(15, 330, 150, 30);

        contentPane.add(leftPanel);
        leftPanel.setBounds(0, 0, 180, 570);

        contentPane.add(rightPanelContainer);
        rightPanelContainer.setBounds(180, 0, 720, 570);

        rightPanelContainer.add(customerMoviesPanel, "movies");
        rightPanelContainer.add(customerReservationPanel, "reservations");
        rightPanelContainer.add(customerProfilePanel, "profile");
        rightPanelContainer.add(customerRatingsPanel, "reviews");
        rightPanelContainer.add(customerPaymentsPanel, "payments");

        cardLayout.show(rightPanelContainer, "movies");

        contentPane.setPreferredSize(new Dimension(900, 600));
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
        
        switch (command) {
            case "All Movies":
                cardLayout.show(rightPanelContainer, "movies");
                break;
            case "Your Reservations":
                cardLayout.show(rightPanelContainer, "reservations");
                break;
            case "    Profile":
                cardLayout.show(rightPanelContainer, "profile");
                break;
            case "Your Reviews":
                cardLayout.show(rightPanelContainer, "reviews");
                break;
                
            case "Your Payments":
                cardLayout.show(rightPanelContainer, "payments");
                break;

            case "Reload Data":
                customerMoviesPanel.setVisible(false);
                customerReservationPanel.setVisible(false);

                customerMoviesPanel = new CustomerMoviesPanel(this.currentUser, this);
                customerReservationPanel = new CustomerReservationPanel(this.currentUser, this);
                customerProfilePanel = new CustomerProfilePanel(this.currentUser);
                customerRatingsPanel = new CustomerRatingsPanel(this.currentUser);

                rightPanelContainer.removeAll();
                rightPanelContainer.add(customerMoviesPanel, "movies");
                rightPanelContainer.add(customerReservationPanel, "reservations");
                rightPanelContainer.add(customerProfilePanel, "profile");
                rightPanelContainer.add(customerRatingsPanel, "reviews");

                cardLayout.show(rightPanelContainer, "movies");

                rightPanelContainer.revalidate();
                rightPanelContainer.repaint();
                break;
            case "Logout": 
                this.setVisible(false);
                LoginForm lf = new LoginForm();
		        lf.setVisible(true);
                break;
        }
	}

}
