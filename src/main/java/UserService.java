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
            try {
                throw new RuntimeException("List of users is empty");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return users;
    }

    public User getUserById(Long id) {
        LogUtil.logger.debug(UserService.class + " - use method getUserById");
        User user = userDAO.getUserById(id);
        if (user == null) {
            try {
                throw new RuntimeException("User with id = " + id + " - not found");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return user;
    }

    public User create(User user) {
        LogUtil.logger.debug(UserService.class + " - use method create");
        User newUser = userDAO.create(user);
        if (newUser == null) {
            try {
                throw new RuntimeException("Failed to create new user!");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return newUser;
    }

    public User update(User user) {
        LogUtil.logger.debug(UserService.class + " - use method update");
        User upUser = userDAO.update(user);
        if (upUser == null) {
            try {
                throw new RuntimeException("User " + user.getId() + " is not update!");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return upUser;
    }

    public void delete(Long id) {
        LogUtil.logger.debug(UserService.class + " - use method delete");
        if (!userDAO.delete(id)) {
            try {
                throw new RuntimeException("Couldn't delete user (id = " + id + ");");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
    }

    public User getUserByEmail(String email) {
        LogUtil.logger.debug(UserService.class + " - use method getUserByEmail");
        User user = userDAO.getUserByEmail(email);
        if (user == null) {
            try {
                throw new RuntimeException("User with email: " + email + " - not founded");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return user;
    }

    public List<User> getUserByLastName(String lastName) {
        LogUtil.logger.debug(UserService.class + " - use method getUserByLastName");
        List<User> users = userDAO.getUserByLastName(lastName);
        if (users.isEmpty()) {
            try {
                throw new RuntimeException("List of users is empty");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return users;
    }

    public int countAllUsers() {
        LogUtil.logger.debug(UserService.class + " - use method countAllUsers");
        int count = userDAO.countAllUsers();
        if (count == 0) {
            try {
                throw new RuntimeException("List of users is empty!");
            } catch (RuntimeException e) {
                LogUtil.logger.error(e);
            }
        }
        return count;
    }
}
