package ru.innopolis.stc12.lab02.service;

import ru.innopolis.stc12.lab02.pojo.Role;
import ru.innopolis.stc12.lab02.pojo.User;

import java.util.List;

public interface UserService {
    int getRoleId(String login);

    boolean checkAuth(String login, String password);

    User getUserByLogin(String login);

    List<User> getUsersMinions(String login);

    Role getRoleByLogin(String login);

    List<User> getAllUsers();

    List<Role> getAllRoles();

    boolean addUser(User user);

}
