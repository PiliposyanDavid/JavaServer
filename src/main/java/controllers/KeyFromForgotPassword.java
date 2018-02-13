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

@WebServlet(name = "KeyFromForgotPassword",urlPatterns = "/setkey")
public class KeyFromForgotPassword extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("pages/setPasswordKey.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password_key = req.getParameter("key");
        String new_password = req.getParameter("password");

        if(!UpdateUser.changePassword(email,password_key,new_password)){
            req.setAttribute("error", Settings.getServerError());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            req.getRequestDispatcher("pages/errorInUser.jsp").forward(req, resp);
        }


        System.out.println(email + password_key + new_password);

        UserforJson user = UpdateUser.findUserinEmailPassword(email, new_password);
        Cookie cookie = new Cookie("key", user.getKey());
        resp.addCookie(cookie);
        resp.sendRedirect("/user");

    }
}
