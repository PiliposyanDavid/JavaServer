package controllers;

import users.UpdateUser;
import users.UserforJson;
import utility.Settings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "UserServlet", urlPatterns = {"/user"})

public class HomeUser extends HttpServlet {

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

                String username = user.getUsername();
                req.setAttribute("username", username);
                req.setAttribute("email", user.getEmail());
                resp.setStatus(HttpServletResponse.SC_OK);
                req.getRequestDispatcher("pages/homeUser.jsp").forward(req, resp);
                return;
            }
        }

        req.setAttribute("error", Settings.getServerError());
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        req.getRequestDispatcher("pages/errorInUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie ck = new Cookie("key", "");
        ck.setMaxAge(0);
        resp.addCookie(ck);
        resp.sendRedirect("/home");
    }
}
