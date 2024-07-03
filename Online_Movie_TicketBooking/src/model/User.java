package model;



public class User {

    public enum Role {
        ADMIN,
        USER,
        THEATRE_ADMIN
    }
    private int userId;
    private String name;
    private String username;
    private String password;
    private String emailId;
    private String mobileNumber;
    private Role role; // admin, user, theatre_admin

    public User(int userId, String name, String username, String password, String emailId, String mobileNumber,Role role){
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }

    public User(String name,String username, String password, String emailId, String mobileNumber,Role role){
        this.username = username;
        this.name = name;
        this.password = password;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.role = role;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
