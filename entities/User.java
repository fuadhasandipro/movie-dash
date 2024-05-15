package entities;

public class User {
    protected String userId, name, email, phoneNo, address, securityAns, password;
    protected int role;

    public User() {}

    public User(String userId, String name, String email, String phoneNo, String address, int role, String securityAns, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
        this.role = role;
        this.securityAns = securityAns;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String toStringUser() {
        return userId + "," + role + "," + securityAns + "," + password + "\n";
    }

    public static User formUser(String str) {
        String[] info = str.split(",");
        User u = new User();
        u.setUserId(info[0]);
        u.setRole(Integer.parseInt(info[1]));
        u.setSecurityAns(info[2]);
        u.setPassword(info[3]);
        return u;
    }
}
