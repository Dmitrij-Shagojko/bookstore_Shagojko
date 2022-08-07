import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAll() {
        LogUtil.logger.debug(UserService.class + " - use method getAll");
        List<User> users = userDAO.getAll();
        if (users.isEmpty()) {
            throw new RuntimeException("List of users is empty");
        }
        return users;
    }

    public User getUserById(Long id) {
        LogUtil.logger.debug(UserService.class + " - use method getUserById");
        User user = userDAO.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User with id = " + id + " - not found");
        }
        return user;
    }

    public User create(User user) {
        LogUtil.logger.debug(UserService.class + " - use method create");
        User newUser = userDAO.create(user);
        if (newUser == null) {
            throw new RuntimeException("Failed to create new user!");
        }
        return newUser;
    }

    public User update(User user) {
        LogUtil.logger.debug(UserService.class + " - use method update");
        User upUser = userDAO.update(user);
        if (upUser == null) {
            throw new RuntimeException("User " + user.getId() + " is not update!");
        }
        return upUser;
    }

    public void delete(Long id) {
        LogUtil.logger.debug(UserService.class + " - use method delete");
        if (!userDAO.delete(id)) {
            throw new RuntimeException("Couldn't delete user (id = " + id + ");");
        }
    }

    public User getUserByEmail(String email) {
        LogUtil.logger.debug(UserService.class + " - use method getUserByEmail");
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("User with email: " + email + " - not founded");
        }
        return user;
    }

    public List<User> getUserByLastName(String lastName) {
        LogUtil.logger.debug(UserService.class + " - use method getUserByLastName");
        List<User> users = userDAO.getUserByLastName(lastName);
        if (users.isEmpty()) {
            throw new RuntimeException("List of users is empty");
        }
        return users;
    }

    public int countAllUsers() {
        LogUtil.logger.debug(UserService.class + " - use method countAllUsers");
        int count = userDAO.countAllUsers();
        if (count == 0) {
            throw new RuntimeException("List of users is empty!");
        }
        return count;
    }
}
