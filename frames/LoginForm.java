package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import repositories.*;
import entities.*;

public class LoginForm extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLabel welcomeLabel;
    private JLabel signInLabel;
    private JLabel userIdLabel;
    private JTextField userIdField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel noAccountLabel;
    private JButton forgotPasswordButton;
    private JButton createAccountButton;
    private JLabel backgroundImageLabel;
    
    public LoginForm() {
        super("Login Form");
        this.setSize(750, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("../assets/img/auth/movie.jpg"));

        mainPanel = new JPanel();
        welcomeLabel = new JLabel();
        signInLabel = new JLabel();
        userIdLabel = new JLabel();
        userIdField = new JTextField();
        passwordLabel = new JLabel();
        passwordField = new JPasswordField();
        loginButton = new JButton();
        noAccountLabel = new JLabel();
        forgotPasswordButton = new JButton();
        createAccountButton = new JButton();
        backgroundImageLabel = new JLabel(backgroundImage);

        mainPanel.setBackground(new Color(0x201f2d));
        mainPanel.setLayout(null);

        mainPanel.add(backgroundImageLabel);
        backgroundImageLabel.setBounds(0, 0, 345, 450);

        //---- welcomeLabel ----
        welcomeLabel.setText("Welcome Back");
        welcomeLabel.setFont(new Font("Lucida Sans", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.white);
        mainPanel.add(welcomeLabel);
        welcomeLabel.setBounds(new Rectangle(new Point(405, 50), welcomeLabel.getPreferredSize()));

        //---- signInLabel ----
        signInLabel.setText("Sign in with your credentials");
        signInLabel.setFont(new Font("Rubik Light", Font.PLAIN, 14));
        signInLabel.setForeground(Color.white);
        mainPanel.add(signInLabel);
        signInLabel.setBounds(405, 80, 240, 30);

        //---- userIdLabel ----
        userIdLabel.setText("User ID");
        userIdLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        userIdLabel.setForeground(Color.white);
        mainPanel.add(userIdLabel);
        userIdLabel.setBounds(405, 140, 240, 30);

        //---- userIdField ----
        userIdField.setBackground(new Color(0x3a3854));
        userIdField.setForeground(Color.white);
        userIdField.setCaretColor(Color.white);
        userIdField.setSelectionColor(new Color(0xf2213d));
        mainPanel.add(userIdField);
        userIdField.setBounds(405, 170, 280, 35);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        passwordLabel.setFont(new Font("Rubik Light", Font.BOLD, 14));
        passwordLabel.setForeground(Color.white);
        mainPanel.add(passwordLabel);
        passwordLabel.setBounds(405, 215, 240, 30);

        //---- passwordField ----
        passwordField.setBackground(new Color(0x3a3854));
        passwordField.setForeground(Color.white);
        passwordField.setCaretColor(Color.white);
        passwordField.setSelectionColor(new Color(0xf2213d));
        mainPanel.add(passwordField);
        passwordField.setBounds(405, 245, 280, 35);

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.setBackground(new Color(0xf2213d));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("Rubik Light", Font.BOLD, 12));
        loginButton.addActionListener(this);
        mainPanel.add(loginButton);
        loginButton.setBounds(405, 300, 110, 35);

        //---- noAccountLabel ----
        noAccountLabel.setText("Don't have an account?");
        noAccountLabel.setFont(new Font("Rubik Light", Font.PLAIN, 14));
        noAccountLabel.setForeground(Color.white);
        mainPanel.add(noAccountLabel);
        noAccountLabel.setBounds(405, 365, 145, 30);

        //---- forgotPasswordButton ----
        forgotPasswordButton.setText("Forgot Password");
        forgotPasswordButton.setForeground(Color.white);
        forgotPasswordButton.setFont(new Font("Rubik Light", Font.BOLD, 12));
        forgotPasswordButton.setBackground(new Color(0x201f2d));
        forgotPasswordButton.addActionListener(this);
        mainPanel.add(forgotPasswordButton);
        forgotPasswordButton.setBounds(555, 310, 135, 20);

        //---- createAccountButton ----
        createAccountButton.setText("Create One");
        createAccountButton.setForeground(new Color(0xf2213d));
        createAccountButton.setFont(new Font("Rubik Light", Font.BOLD, 12));
        createAccountButton.addActionListener(this);
        mainPanel.add(createAccountButton);
        createAccountButton.setBounds(545, 370, 105, 20);
        
        add(mainPanel);
    }

	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();

		if (command.equals(createAccountButton.getText())) {
			CreateAccountFrame createAccount = new CreateAccountFrame();
			this.setVisible(false);
			createAccount.setVisible(true);
            createAccount.repaint();
		}

        if (command.equals(loginButton.getText())) {
            String userId = userIdField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (userId.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "User ID and Password are required.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserRepo userRepo = new UserRepo();
            User user = userRepo.searchUserByUserId(userId);

            if (user == null || !user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Invalid User ID or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else {
                JOptionPane.showMessageDialog(this, "Login successful!");

                CustomerView customerPanel = new CustomerView(user);
                this.setVisible(false);
                customerPanel.setVisible(true);
                customerPanel.repaint();
            }

        }

        if (command.equals(forgotPasswordButton.getText())) {
            String userId = userIdField.getText().trim();

            if (userId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your User ID to reset your password.", "Forgot Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            UserRepo userRepo = new UserRepo();
            User user = userRepo.searchUserByUserId(userId);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "User ID not found.", "Forgot Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String securityAns = JOptionPane.showInputDialog(this, "Security Key:", "Forgot Password", JOptionPane.PLAIN_MESSAGE);

            if (securityAns == null) {
                return;
            }

            if (!user.getSecurityAns().equals(securityAns)) {
                JOptionPane.showMessageDialog(this, "Incorrect Security Key.", "Forgot Password", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newPassword = JOptionPane.showInputDialog(this, "Enter your new password:", "Forgot Password", JOptionPane.PLAIN_MESSAGE);

            if (newPassword == null) {
                return;
            }

            user.setPassword(newPassword);
            userRepo.updateUser(user);

            JOptionPane.showMessageDialog(this, "Password reset successfully. Please login with your new password.", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
}
