package repositories;
import entities.User;
import interfaces.IUserRepo;

public class UserRepo implements IUserRepo {

    private static final String USERS_FILE_PATH = "repositories/db/users.txt";
    private static final int MAX_USERS = 100; 

    public void addUser(User user) {
        User[] userList = getAllUsers();

        for (int i = 0; i < userList.length; i++) {
            if (userList[i] == null) {
                userList[i] = user;
                break;
            }
        }

        write(userList);
    }

    public void removeUser(String userId) {
        User[] userList = getAllUsers();

        for (int i = 0; i < userList.length; i++) {
            if (userList[i] != null && userList[i].getUserId().equals(userId)) {
                userList[i] = null;
                break;
            }
        }

        write(userList);
    }

    public void updateUser(User user) {
        User[] userList = getAllUsers();

        for (int i = 0; i < userList.length; i++) {
            if (userList[i] != null && userList[i].getUserId().equals(user.getUserId())) {
                userList[i] = user;
                break;
            }
        }

        write(userList);
    }

    public User searchUserByUserId(String userId) {
        User[] userList = getAllUsers();

        for (User user : userList) {
            if (user != null && user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }

    public User[] getAllUsers() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(USERS_FILE_PATH);

        User[] userList = new User[MAX_USERS];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                User user = User.formUser(str);
                userList[index] = user;
                index++;
            }
        }

        return userList;
    }

    public void write(User[] userList) {
        String[] data = new String[MAX_USERS];
        for (int i = 0; i < userList.length; i++) {
            if (userList[i] != null) {
                data[i] = userList[i].toString();
            }
        }

        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, USERS_FILE_PATH);
    }
}
