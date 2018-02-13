package controllers;

import users.UpdateUser;
import users.UserforJson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class Home extends HttpServlet {
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
        req.getRequestDispatcher("pages/home.jsp").forward(req, resp);
    }



}

