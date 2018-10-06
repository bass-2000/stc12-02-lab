package ru.innopolis.stc12.lab02.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.lab02.pojo.User;
import ru.innopolis.stc12.lab02.repository.dao.UserDaoImpl;
import ru.innopolis.stc12.lab02.service.UserService;
import ru.innopolis.stc12.lab02.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DashboardController extends HttpServlet {
    static final String LOGIN = "login";
    private static Logger logger = Logger.getLogger(DashboardController.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String login = (String) req.getSession().getAttribute(LOGIN);
        List<User> minions = userService.getUsersMinions(login);
        User currentUser = userService.getUserByLogin(login);
        req.setAttribute("minions", minions);
        req.setAttribute("currentUserSalary", currentUser.getSalary());
        try {
            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (ServletException | IOException se) {
            logger.error(se.getMessage());
        }
    }
}
