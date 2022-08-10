package com.company.dao.impl;

import com.company.dao.UserDAO;
import com.company.dao.connection.DataSource;
import com.company.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger log = LogManager.getLogger(UserDAOImpl.class);
    public static final String GET_ALL_USER = """
            SELECT u.id, u.first_name, u.last_name, u.email, r.role AS role
            FROM users u
            JOIN roles r
            ON u.role_id = r.id
            ORDER BY u.id""";
    public static final String GET_USER_BY_ID = """
            SELECT u.id, u.first_name, u.last_name, u.email, r.role AS role
            FROM users u
            JOIN roles r
            ON u.role_id = r.id
            WHERE u.id = ?""";
    public static final String CREATE_USER = """
            INSERT INTO users (first_name, last_name, email, role_id)
            VALUES (?, ?, ?, (SELECT id FROM roles WHERE role =?))""";
    public static final String GET_USER_BY_EMAIL = """
            SELECT u.id, u.first_name, u.last_name, u.email, r.role AS role
            FROM users u
            JOIN roles r
            ON u.role_id = r.id
            WHERE u.email = ?""";
    public static final String UPDATE_USER = """
            UPDATE users
            SET first_name=?, last_name=?, email=?, role_id=(SELECT id FROM roles WHERE role=?)
            WHERE id=?""";
    public static final String DELETE_USER = """
            DELETE FROM users
            WHERE id = ?""";
    public static final String GET_USER_BY_LASTNAME = """
            SELECT u.id, u.first_name, u.last_name, u.email, r.role AS role
            FROM users u
            JOIN roles r
            ON u.role_id = r.id
            WHERE u.last_name = ?""";
    public static final String COUNT_ALL_USERS = """
            SELECT count(*) AS rows 
            FROM users""";

    private DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private User setUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(User.Role.valueOf(resultSet.getString("role")));
        return user;
    }

    @Override
    public List<User> getAll() {
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USER);
            while (resultSet.next()) {
                User user = setUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return setUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User create(User user) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getRole()));
            if (statement.executeUpdate() == 1) {
                return getUserByEmail(user.getEmail());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setLong(5, user.getId());
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getRole()));
            if (statement.executeUpdate() == 1) {
                return getUserById(user.getId());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = dataSource.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return setUser(resultSet);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LASTNAME)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = setUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public int countAllUsers() {
        Connection connection = dataSource.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(COUNT_ALL_USERS);
            if (resultSet.next()) {
                return resultSet.getInt("rows");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        throw new RuntimeException("Table users is empty!!!");
    }
}
