import java.lang.*;
import frames.*;
import entities.*;

public class Start {
	public static void main(String[] args) {

		User testUser = new User(
            "1", "John Doe", "john.doe@example.com", "1234567890", "123 Main St", 1, "My first pet", "password123"
        );

		AdminView lf = new AdminView(testUser);
		lf.setVisible(true);
	}
}