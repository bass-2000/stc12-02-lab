package ru.innopolis.stc12.lab02.repository.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.lab02.pojo.Role;
import ru.innopolis.stc12.lab02.pojo.User;
import ru.innopolis.stc12.lab02.repository.connectionManager.ConnectionManager;
import ru.innopolis.stc12.lab02.repository.connectionManager.ConnectionManagerJdbcImpl;
import ru.innopolis.stc12.lab02.service.utils.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * from users inner join roles on users.roleid = roles.id WHERE username = ?");) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            new Role(resultSet.getInt("roleid"), resultSet.getString("role")), resultSet.getInt("chiefid"), resultSet.getInt("salary"));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        return user;
    }

    @Override
    public List<User> getUsersMinions(String login) {
        List<User> preResult = new ArrayList<>();
        List<User> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * from users inner join roles on users.roleid = roles.id WHERE chiefid = (SELECT id from Users where username = ?) and users.id not in (SELECT id from Users where username = ?)");) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                preResult.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                        new Role(resultSet.getInt("roleid"), resultSet.getString("role")),
                        resultSet.getInt("chiefid"),
                        resultSet.getInt("salary")));
            }
            result.addAll(preResult);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        for (User user : preResult) {
            result.addAll(getUsersMinions(user.getUsername()));
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * from users inner join roles on users.roleid = roles.id ");) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                            new Role(resultSet.getInt("roleid"), resultSet.getString("role")),
                            resultSet.getInt("chiefid"),
                            resultSet.getInt("salary")));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> result = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * from roles ");) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    result.add(new Role(resultSet.getInt("id"), resultSet.getString("role")));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        return result;
    }

    @Override
    public boolean addUser(User user) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?)");
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, HashUtil.stringToMD5(user.getPassword()));
            statement.setInt(3, user.getRole().getId());
            statement.setInt(4, user.getChiefId());
            statement.setInt(5, user.getSalary());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }
}
