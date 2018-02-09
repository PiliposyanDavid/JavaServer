package controllers;

import users.UpdateUser;
import users.UserforJson;
import utility.Settings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})


public class HomeLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        String cookeKey = null;
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) continue;
                cookeKey = cookie.getValue().toString();
            }

            if (cookeKey != null) {
                UserforJson user = UpdateUser.findUserinKey(cookeKey);

                if (user != null) {
                    resp.sendRedirect("/user");
                    return;
                }
            }
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("pages/homeLogin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String password = req.getParameter("password");
        String email = req.getParameter("email").toLowerCase();


        if (password == null && email == null) {

            req.setAttribute("error", Settings.getPasswordEmailNull());
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.getRequestDispatcher("pages/errorInLogin.jsp").forward(req, resp);
            return;
        }

        UserforJson user = UpdateUser.findUserinEmailPassword(email, password);


        if (user == null) {

            req.setAttribute("error", Settings.getUserNotFound());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            req.getRequestDispatcher("pages/errorInLogin.jsp").forward(req, resp);
            return;
        }

        Cookie cookie = new Cookie("key", user.getKey());
        resp.addCookie(cookie);
        resp.sendRedirect("/user");
    }
}
