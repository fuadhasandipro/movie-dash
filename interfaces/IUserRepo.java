package interfaces;
import java.lang.*;
import entities.*;


public interface IUserRepo {
	void addUser(User u);
	void removeUser(String userId);
	void updateUser(User user);
	User searchUserByUserId(String userId);
	User[] getAllUser();
}