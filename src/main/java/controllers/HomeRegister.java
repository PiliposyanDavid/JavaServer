package controllers;

import send.mail.SendMail;
import users.UpdateUser;
import users.UserforJson;
import utility.Settings;
import utility.Validators;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Enumeration;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class HomeRegister extends HttpServlet {
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
        req.getRequestDispatcher("pages/homeRegister.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String email = req.getParameter("email").toLowerCase();
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        if (!Validators.isEmailValid(email)) {

            req.setAttribute("error", Settings.getIncorrectEmail());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
            return;
        }


        if (!Validators.isPasswordValid(password)) {

            req.setAttribute("error", Settings.getIncorectPassword());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
            return;
        }

        if (!Validators.isUsernameValid(username)) {

            req.setAttribute("error", Settings.getIncoreectUsername());
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
            return;
        }

        UserforJson user = UpdateUser.findUser(email, username, password);

        if (user != null) {

            Cookie cookie = new Cookie("key", user.getKey());
            resp.addCookie(cookie);
            req.setAttribute("error", Settings.getOlrediRegister());
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            req.getRequestDispatcher("pages/olredyRegistred.jsp").forward(req, resp);
            return;

        }


        if (UpdateUser.FindUserinEmail(email) != null) {

            req.setAttribute("error", Settings.getEmailOlredyRegistred());
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
            return;
        }


        try {
            if (!UpdateUser.isAddUser(email, username, password)) {

                req.setAttribute("error", Settings.getErrorUnknown());
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
                return;
            }
        } catch (NoSuchProviderException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        user = UpdateUser.findUser(email, username, password);
        Cookie cookie = new Cookie("key", user.getKey());
        resp.addCookie(cookie);
        resp.sendRedirect("/user");
    }
}
