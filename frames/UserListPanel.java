package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.UUID;
import entities.*;
import repositories.*;

public class UserListPanel extends JPanel implements ActionListener {

    private JLabel headerTitle;
    private JTable userTable;
    private JScrollPane userTableSP;

    private JLabel userIdLabel; 
    private JTextField userIdField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel phoneNoLabel;
    private JTextField phoneNoField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel roleLabel;
    private JComboBox<String> roleComboBox;
    private JLabel securityAnsLabel;
    private JTextField securityAnsField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JButton addButton;
    private JButton fillFormButton;
    private JButton updateButton;
    private UserRepo userRepo;

    public UserListPanel() {
        userRepo = new UserRepo();
        setBackground(new Color(0x201f2d));
        setLayout(null);

        headerTitle = new JLabel("Users");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);

        userIdLabel = createLabel("User ID"); 
        userIdLabel.setBounds(30, 40, 60, 25); 
        add(userIdLabel); 

        userIdField = createTextField(); 
        userIdField.setBounds(100, 40, 120, 25);
        add(userIdField); 

        nameLabel = createLabel("Name");
        nameLabel.setBounds(30, 70, 60, 25);
        add(nameLabel);

        nameField = createTextField();
        nameField.setBounds(100, 70, 120, 25);
        add(nameField);

        emailLabel = createLabel("Email");
        emailLabel.setBounds(240, 70, 60, 25);
        add(emailLabel);

        emailField = createTextField();
        emailField.setBounds(310, 70, 120, 25);
        add(emailField);

        phoneNoLabel = createLabel("Phone No");
        phoneNoLabel.setBounds(450, 70, 100, 25);
        add(phoneNoLabel);

        phoneNoField = createTextField();
        phoneNoField.setBounds(560, 70, 120, 25);
        add(phoneNoField);

        addressLabel = createLabel("Address");
        addressLabel.setBounds(30, 105, 80, 25);
        add(addressLabel);

        addressField = createTextField();
        addressField.setBounds(120, 105, 520, 25);
        add(addressField);

        roleLabel = createLabel("Role");
        roleLabel.setBounds(30, 140, 100, 25);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"Admin", "Staff", "User"});
        roleComboBox.setBounds(140, 140, 120, 25);
        add(roleComboBox);

        securityAnsLabel = createLabel("Security Ans");
        securityAnsLabel.setBounds(280, 140, 110, 25);
        add(securityAnsLabel);

        securityAnsField = createTextField();
        securityAnsField.setBounds(390, 140, 250, 25);
        add(securityAnsField);

        passwordLabel = createLabel("Password");
        passwordLabel.setBounds(30, 175, 100, 25);
        add(passwordLabel);

        passwordField = createTextField();
        passwordField.setBounds(140, 175, 120, 25);
        add(passwordField);

        addButton = new JButton("Add User");
        addButton.setBounds(30, 210, 100, 25);
        addButton.addActionListener(this);
        add(addButton);

        fillFormButton = new JButton("Fill Form");
        fillFormButton.setBounds(140, 210, 120, 25);
        fillFormButton.addActionListener(this);
        add(fillFormButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(270, 210, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        String[] userColumns = {"User ID", "Name", "Email", "Phone No", "Address", "Role", "Security Answer"};
        User[] users = userRepo.getAllUsers();
        String[][] userData = new String[users.length][7];

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                userData[i][0] = users[i].getUserId();
                userData[i][1] = users[i].getName();
                userData[i][2] = users[i].getEmail();
                userData[i][3] = users[i].getPhoneNo();
                userData[i][4] = users[i].getAddress();
                userData[i][5] = String.valueOf(users[i].getRole());
                userData[i][6] = users[i].getSecurityAns();
            }
        }

        userTable = new JTable(userData, userColumns);
        userTable.setBackground(new Color(0xb8b3fc));
        userTableSP = new JScrollPane(userTable);
        userTableSP.setBounds(30, 250, 660, 325);
        add(userTableSP);
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
            String userId = userIdField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNo = phoneNoField.getText().trim();
            String address = addressField.getText().trim();
            int role = roleComboBox.getSelectedIndex() + 1; // Admin = 1, Staff = 2, User = 3
            String securityAns = securityAnsField.getText().trim();
            String password = passwordField.getText().trim();

            if (validateInput(userId, email, name, phoneNo, address, securityAns, password)) {
                User user = new User(userId, name, email, phoneNo, address, role, securityAns, password);

                
                if (userRepo.searchUserByUserId(userId) != null) {
                    JOptionPane.showMessageDialog(this, "User with this ID already exists.", "Adding Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userRepo.searchUserByEmail(email) != null) {
                    JOptionPane.showMessageDialog(this, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (userRepo.searchUserByPhone(phoneNo) != null) {
                    JOptionPane.showMessageDialog(this, "Phone number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                userRepo.addUser(user);
                JOptionPane.showMessageDialog(this, "User added successfully!");

                clearFields();
                refreshTable();
            }
        }

        if (e.getSource() == fillFormButton) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userId = userTable.getValueAt(selectedRow, 0).toString();
                User user = userRepo.searchUserByUserId(userId);

                if (user != null) {
                    nameField.setText(user.getName());
                    emailField.setText(user.getEmail());
                    phoneNoField.setText(user.getPhoneNo());
                    addressField.setText(user.getAddress());
                    roleComboBox.setSelectedIndex(user.getRole() - 1);
                    securityAnsField.setText(user.getSecurityAns());
                    passwordField.setText(user.getPassword());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == updateButton) {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String userId = userTable.getValueAt(selectedRow, 0).toString();
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phoneNo = phoneNoField.getText().trim();
                String address = addressField.getText().trim();
                int role = roleComboBox.getSelectedIndex() + 1; // Admin = 1, Staff = 2, User = 3
                String securityAns = securityAnsField.getText().trim();
                String password = passwordField.getText().trim();

                if (validateInput(userId, email, name, phoneNo, address, securityAns, password)) {
                    User user = new User(userId, name, email, phoneNo, address, role, securityAns, password);

                    User existingUserByEmail = userRepo.searchUserByEmail(email);
                    if (existingUserByEmail != null && !existingUserByEmail.getUserId().equals(userId)) {
                        JOptionPane.showMessageDialog(this, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    User existingUserByPhone = userRepo.searchUserByPhone(phoneNo);
                    if (existingUserByPhone != null && !existingUserByPhone.getUserId().equals(userId)) {
                        JOptionPane.showMessageDialog(this, "Phone number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    userRepo.updateUser(user);
                    JOptionPane.showMessageDialog(this, "User updated successfully!");

                    clearFields();
                    refreshTable();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateInput(String userId, String email, String name, String phoneNo, String address, String securityAns, String password) {
        if (name.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || address.isEmpty() || securityAns.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!phoneNo.matches("^\\+?[0-9]{10,13}$")) {
            JOptionPane.showMessageDialog(this, "Invalid phone number format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void clearFields() {
        userIdField.setText("");
        nameField.setText("");
        emailField.setText("");
        phoneNoField.setText("");
        addressField.setText("");
        roleComboBox.setSelectedIndex(0);
        securityAnsField.setText("");
        passwordField.setText("");
    }

    private void refreshTable() {
        String[] userColumns = {"User ID", "Name", "Email", "Phone No", "Address", "Role", "Security Answer"};
        User[] users = userRepo.getAllUsers();
        String[][] userData = new String[users.length][7];

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                userData[i][0] = users[i].getUserId();
                userData[i][1] = users[i].getName();
                userData[i][2] = users[i].getEmail();
                userData[i][3] = users[i].getPhoneNo();
                userData[i][4] = users[i].getAddress();
                userData[i][5] = String.valueOf(users[i].getRole());
                userData[i][6] = users[i].getSecurityAns();
            }
        }

        remove(userTableSP);

        userTable = new JTable(userData, userColumns);
        userTable.setBackground(new Color(0xb8b3fc));
        userTableSP = new JScrollPane(userTable);
        userTableSP.setBounds(30, 250, 660, 325);
        add(userTableSP);

        revalidate();
        repaint();
    }
}
