import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAll() {
        return null;
    }

    public User getUserById(Long id) {
        return null;
    }

    public User create(User user) {
        return null;
    }

    public User update(User user) {
        return null;
    }

    public void delete(Long id) {

    }

    public User getUserByEmail(String email) {
        return null;
    }

    public List<User> getUserByLastName(String lastName) {
        return null;
    }

    public int countAllUsers() {
        return 0;
    }
}
