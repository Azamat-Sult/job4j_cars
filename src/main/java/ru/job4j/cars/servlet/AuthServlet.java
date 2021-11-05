package ru.job4j.cars.servlet;

import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarsService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User dbUser = CarsService.instOf().findUserByEmail(email);
        if (dbUser != null && password.equals(dbUser.getPassword())) {
            HttpSession sc = req.getSession();
            sc.setAttribute("user", dbUser);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
