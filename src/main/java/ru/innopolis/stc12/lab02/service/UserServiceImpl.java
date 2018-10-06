package ru.innopolis.stc12.lab02.service;


import ru.innopolis.stc12.lab02.pojo.Role;
import ru.innopolis.stc12.lab02.pojo.User;
import ru.innopolis.stc12.lab02.repository.dao.UserDao;
import ru.innopolis.stc12.lab02.service.utils.HashUtil;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public int getRoleId(String login) {
        User user;
        if (login != null) {
            user = userDao.getUserByLogin(login);
            if (user == null) {
                return -1;
            }
            return user.getRole().getId();
        }
        return -2;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        User user;
        if ((login != null) && (password != null)) {
            user = userDao.getUserByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(HashUtil.stringToMD5(password))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        if (login != null) {
            user = userDao.getUserByLogin(login);
        }
        return user;
    }

    @Override
    public List<User> getUsersMinions(String login) {
        if (login != null) {
            return userDao.getUsersMinions(login);
        }
        return new ArrayList<>();
    }

    @Override
    public Role getRoleByLogin(String login) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Role> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

}
