package dao;

import model.User;
import java.util.List;

public interface UserDAO {
     boolean addUser(User user);
    User getUserById(int userId);
    User getUserByUsernameAndPassword(String username,String password);
    List<User> getAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int userId);
}

