package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccountFrame extends JFrame implements ActionListener {

	private JPanel accountPanel;
	private JLabel welcomeLabel;
	private JLabel createAccountLabel;
	private JLabel userIdLabel;
	private JTextField userIdField;
	private JLabel emailLabel;
	private JTextField emailField;
	private JButton registerButton;
	private JLabel nameLabel;
	private JTextField nameField;
	private JTextField phoneField;
	private JLabel phoneLabel;
	private JTextField addressField;
	private JLabel addressLabel;
	private JTextField securityField;
	private JLabel securityLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JLabel bgImageLabel;
    
    public CreateAccountFrame () {

        super("Registration Form");
        this.setSize(750, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("movie2.jpg"));

		accountPanel = new JPanel();
		welcomeLabel = new JLabel();
		createAccountLabel = new JLabel();
		userIdLabel = new JLabel();
		userIdField = new JTextField();
		emailLabel = new JLabel();
		emailField = new JTextField();
		registerButton = new JButton();
		nameLabel = new JLabel();
		nameField = new JTextField();
		phoneField = new JTextField();
		phoneLabel = new JLabel();
		addressField = new JTextField();
		addressLabel = new JLabel();
		securityField = new JTextField();
		securityLabel = new JLabel();
		passwordLabel = new JLabel();
		passwordField = new JPasswordField();
		bgImageLabel = new JLabel(backgroundImage);

        accountPanel.setBackground(new Color(0x201f2d));
        accountPanel.setLayout(null);

        //---- welcomeLabel ----
        welcomeLabel.setText("Welcome to Ritto Movie");
        welcomeLabel.setFont(new Font("Lucida Sans", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.white);
        accountPanel.add(welcomeLabel);
        welcomeLabel.setBounds(45, 40, 300, 25);

        //---- createAccountLabel ----
        createAccountLabel.setText("Create Your Account");
        createAccountLabel.setFont(new Font("Rubik Light", Font.PLAIN, 14));
        createAccountLabel.setForeground(Color.white);
        accountPanel.add(createAccountLabel);
        createAccountLabel.setBounds(45, 65, 235, 25);

        //---- userIdLabel ----
        userIdLabel.setText("User ID");
        userIdLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        userIdLabel.setForeground(Color.white);
        accountPanel.add(userIdLabel);
        userIdLabel.setBounds(45, 110, 125, 25);

        //---- userIdField ----
        userIdField.setBackground(new Color(0x3a3854));
        userIdField.setForeground(Color.white);
        userIdField.setCaretColor(Color.white);
        userIdField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(userIdField);
        userIdField.setBounds(45, 135, 130, 25);

        //---- emailLabel ----
        emailLabel.setText("Email");
        emailLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        emailLabel.setForeground(Color.white);
        accountPanel.add(emailLabel);
        emailLabel.setBounds(45, 165, 235, 25);

        //---- emailField ----
        emailField.setBackground(new Color(0x3a3854));
        emailField.setForeground(Color.white);
        emailField.setCaretColor(Color.white);
        emailField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(emailField);
        emailField.setBounds(45, 190, 275, 25);

        //---- registerButton ----
        registerButton.setText("Register");
        registerButton.setBackground(new Color(0xf2213d));
        registerButton.setForeground(Color.white);
        registerButton.setFont(new Font("Rubik Light", Font.BOLD, 12));
        accountPanel.add(registerButton);
        registerButton.setBounds(45, 390, 125, 30);

        //---- bgImageLabel ----
        accountPanel.add(bgImageLabel);
        bgImageLabel.setBounds(405, 0, 345, 450);

        //---- nameLabel ----
        nameLabel.setText("Name");
        nameLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        nameLabel.setForeground(Color.white);
        accountPanel.add(nameLabel);
        nameLabel.setBounds(195, 110, 125, 25);

        //---- nameField ----
        nameField.setBackground(new Color(0x3a3854));
        nameField.setForeground(Color.white);
        nameField.setCaretColor(Color.white);
        nameField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(nameField);
        nameField.setBounds(190, 135, 130, 25);

        //---- phoneField ----
        phoneField.setBackground(new Color(0x3a3854));
        phoneField.setForeground(Color.white);
        phoneField.setCaretColor(Color.white);
        phoneField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(phoneField);
        phoneField.setBounds(45, 245, 275, 25);

        //---- phoneLabel ----
        phoneLabel.setText("Phone");
        phoneLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        phoneLabel.setForeground(Color.white);
        accountPanel.add(phoneLabel);
        phoneLabel.setBounds(45, 220, 235, 25);

        //---- addressField ----
        addressField.setBackground(new Color(0x3a3854));
        addressField.setForeground(Color.white);
        addressField.setCaretColor(Color.white);
        addressField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(addressField);
        addressField.setBounds(45, 300, 125, 25);

        //---- addressLabel ----
        addressLabel.setText("Address");
        addressLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        addressLabel.setForeground(Color.white);
        accountPanel.add(addressLabel);
        addressLabel.setBounds(45, 275, 120, 25);

        //---- securityField ----
        securityField.setBackground(new Color(0x3a3854));
        securityField.setForeground(Color.white);
        securityField.setCaretColor(Color.white);
        securityField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(securityField);
        securityField.setBounds(185, 300, 125, 25);

        //---- securityLabel ----
        securityLabel.setText("Security Ans.");
        securityLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        securityLabel.setForeground(Color.white);
        accountPanel.add(securityLabel);
        securityLabel.setBounds(185, 275, 120, 25);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        passwordLabel.setForeground(Color.white);
        accountPanel.add(passwordLabel);
        passwordLabel.setBounds(45, 330, 235, 25);

        //---- passwordField ----
        passwordField.setBackground(new Color(0x3a3854));
        passwordField.setSelectionColor(new Color(0xf2213d));
        accountPanel.add(passwordField);
        passwordField.setBounds(45, 355, 275, 25);
        
        add(accountPanel);
    }

	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
	}
	
}
