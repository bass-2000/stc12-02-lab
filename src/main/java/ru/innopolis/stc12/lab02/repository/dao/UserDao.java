package ru.innopolis.stc12.lab02.repository.dao;

import ru.innopolis.stc12.lab02.pojo.Role;
import ru.innopolis.stc12.lab02.pojo.User;

import java.util.List;


public interface UserDao {
    User getUserByLogin(String login);

    List<User> getUsersMinions(String login);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    boolean addUser(User user);
}
