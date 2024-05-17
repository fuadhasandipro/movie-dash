package utils;

import javax.swing.JOptionPane;

public class ValidationUtils {

    public static boolean validateInput(String userId, String email, String name, String phone, String address, String securityAns, String password) {
        if (userId.isEmpty() || email.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty() || securityAns.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!phone.matches("^\\d{11}$")) {
            JOptionPane.showMessageDialog(null, "Invalid phone number. It should be 11 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
