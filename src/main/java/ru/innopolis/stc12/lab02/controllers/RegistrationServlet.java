package ru.innopolis.stc12.lab02.controllers;

import org.apache.log4j.Logger;
import ru.innopolis.stc12.lab02.pojo.Role;
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

public class RegistrationServlet extends HttpServlet {
    static final String LOGIN = "login";
    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    String action;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl(new UserDaoImpl());
        action = "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        int chiefLogin = 0;
        if (req.getParameter("chief[]") != null) chiefLogin = Integer.parseInt(req.getParameter("chief[]"));
        else action = "nullable_chief";
        int salary = 0;
        if (!req.getParameter("salary").equals("")) salary = Integer.parseInt(req.getParameter("salary"));
        else action = "empty_salary";
        int role = 0;
        if (req.getParameter("roles[]") != null) role = Integer.parseInt(req.getParameter("roles[]"));
        else action = "nullable_roles";
        if ((password.equals("")) || (password == null) || (!password.equals(password2))) action = "wrong_password";
        User user = new User(login, password, new Role(role), chiefLogin, salary);
        if (action.equals("") && userService.addUser(user))
            action = "success";
        resp.sendRedirect("/inner/registration?action=" + action);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        req.setAttribute("allUsers", allUsers);
        List<Role> roles = userService.getAllRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }
}
