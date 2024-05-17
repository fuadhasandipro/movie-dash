package frames;

import entities.User;
import repositories.UserRepo;
import utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerProfilePanel extends JPanel {
    private User currentUser;
    private UserRepo userRepo;

    private JTextField userIdField, emailField, nameField, phoneField, addressField;
    private JPasswordField passwordField, securityField;

    public CustomerProfilePanel(User user) {
        this.currentUser = user;
        this.userRepo = new UserRepo();

        setBackground(new Color(0x201f2d));
        setLayout(null);

        JLabel headerTitle = new JLabel("User Profile");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
        headerTitle.setBounds(30, 40, 230, headerTitle.getPreferredSize().height);

        addFormFields();
        addUpdateButton();
        addChangePasswordButton();
    }

    private void addFormFields() {
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setForeground(Color.WHITE);
        userIdLabel.setBounds(30, 80, 200, 30);
        add(userIdLabel);

        userIdField = new JTextField(currentUser.getUserId());
        userIdField.setBounds(250, 80, 200, 30);
        userIdField.setEnabled(false);
        add(userIdField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(30, 120, 200, 30);
        add(emailLabel);

        emailField = new JTextField(currentUser.getEmail());
        emailField.setBounds(250, 120, 200, 30);
        add(emailField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(30, 160, 200, 30);
        add(nameLabel);

        nameField = new JTextField(currentUser.getName());
        nameField.setBounds(250, 160, 200, 30);
        add(nameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneLabel.setBounds(30, 200, 200, 30);
        add(phoneLabel);

        phoneField = new JTextField(currentUser.getPhoneNo());
        phoneField.setBounds(250, 200, 200, 30);
        add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(Color.WHITE);
        addressLabel.setBounds(30, 240, 200, 30);
        add(addressLabel);

        addressField = new JTextField(currentUser.getAddress());
        addressField.setBounds(250, 240, 200, 30);
        add(addressField);

        JLabel securityLabel = new JLabel("Security Answer:");
        securityLabel.setForeground(Color.WHITE);
        securityLabel.setBounds(30, 280, 200, 30);
        add(securityLabel);

        securityField = new JPasswordField(currentUser.getSecurityAns());
        securityField.setBounds(250, 280, 200, 30);
        add(securityField);
    }

    private void addUpdateButton() {
        JButton updateButton = new JButton("Update Details");
        updateButton.setBounds(250, 320, 200, 30);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String email = emailField.getText().trim();
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                String address = addressField.getText().trim();
                String securityAns = new String(securityField.getPassword()).trim();

                if (ValidationUtils.validateInput(currentUser.getUserId(), email, name, phone, address, securityAns, currentUser.getPassword())) {
                    if (!currentUser.getEmail().equals(email) && userRepo.searchUserByEmail(email) != null) {
                        JOptionPane.showMessageDialog(null, "Email already exists.", "Update Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    currentUser.setEmail(email);
                    currentUser.setName(name);
                    currentUser.setPhoneNo(phone);
                    currentUser.setAddress(address);
                    currentUser.setSecurityAns(securityAns);

                    userRepo.updateUser(currentUser);
                    JOptionPane.showMessageDialog(null, "Details updated successfully!");
                }
            }
        });
        add(updateButton);
    }


    private void addChangePasswordButton() {
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.setBounds(250, 360, 200, 30);
        changePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String securityAns = JOptionPane.showInputDialog(null, "Enter Security Answer:");
                if (securityAns == null) return;

                if (!currentUser.getSecurityAns().equals(securityAns)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Security Answer.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String newPassword = JOptionPane.showInputDialog(null, "Enter New Password:");
                if (newPassword == null) return;

                currentUser.setPassword(newPassword);
                userRepo.updateUser(currentUser);

                JOptionPane.showMessageDialog(null, "Password changed successfully!");
            }
        });
        add(changePasswordButton);
    }
}
