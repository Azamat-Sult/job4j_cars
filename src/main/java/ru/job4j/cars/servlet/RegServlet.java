package ru.job4j.cars.servlet;

import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarsService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String userName = req.getParameter("userName");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        User dbUser = CarsService.instOf().findUserByEmail(email);
        if (dbUser == null) {
            User newUser = User.of(email, phone, userName, password);
            CarsService.instOf().addUser(newUser);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", newUser);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("error", "Пользователь с такой почтой уже зарегистрирован");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
