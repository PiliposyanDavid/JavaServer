package controllers;

import send.mail.SendMail;
import users.UpdateUser;
import utility.Settings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ForgotPassword", urlPatterns = "/forgot")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("pages/retryPasswordHome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email").toLowerCase();

        if (UpdateUser.FindUserinEmail(email) == null) {
            req.setAttribute("error", Settings.getEmailOlredyRegistred());
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            req.getRequestDispatcher("pages/errorInRegister.jsp").forward(req, resp);
            return;
        }

        int number = (1000 + (int) (Math.random() * 9999));
        String password_key = Integer.toString(number);

        SendMail.send(password_key, email);
        UpdateUser.addKeyFromPassword(email,password_key);

        req.setAttribute("email", email);
        resp.setStatus(HttpServletResponse.SC_OK);
        req.getRequestDispatcher("pages/setPasswordKey.jsp").forward(req, resp);


    }
}
