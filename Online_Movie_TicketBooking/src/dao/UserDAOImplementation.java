package dao;

import model.User;
import util.Database;
import util.SQLQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImplementation implements UserDAO {
   public UserDAOImplementation(){

   }
    @Override
    public boolean addUser(User user) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.INSERT_USER);
            ps.setString(1,user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmailId());
            ps.setString(5, user.getMobileNumber());
            ps.setString(6, user.getRole().toString());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int userId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_USER_BY_ID);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmailId(rs.getString("email_id"));
                user.setMobileNumber(rs.getString("mobile_number"));
                user.setRole(User.Role.valueOf(rs.getString("role")));
                return user;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_USER_BY_USERNAME_AND_PASSWORD);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmailId(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobile"));
                user.setRole(User.Role.valueOf(rs.getString("role").toUpperCase()));
                // for Converting role from string to enum

                return user;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_USERS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmailId(rs.getString("email_id"));
                user.setMobileNumber(rs.getString("mobile_number"));
                user.setRole(User.Role.valueOf(rs.getString("role")));

                users.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.UPDATE_USER);
            ps.setInt(1, user.getUserId());
            ps.setString(2,user.getName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getEmailId());
            ps.setString(6, user.getMobileNumber());
            ps.setString(7, user.getRole().toString());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        try {

            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.DELETE_USER);
            ps.setInt(1, userId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User loginUser(String username, String password) {
        return getUserByUsernameAndPassword(username,password);
    }
}

