package org.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.webapp.service.LoginService;
import org.webapp.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/cookie-login", "/login.html"})
public class LoginServlet extends HttpServlet {
    private static final String USERNAME= "admin@gmail.com";
    private static final String PASSWORD= "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginService auth =  new LoginServiceSessionImpl();
        Optional<String> sessionOptional = auth.getUsername(req);

        if (sessionOptional.isPresent()) {
            resp.setContentType("text/html; charset=UTF-8");
           try (PrintWriter out = resp.getWriter()){
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("    <head>");
               out.println("       <meta charset=\"UTF-8\">");
               out.println("       <title> Olá " + sessionOptional.get() + "</title>");
               out.println("    </head>");
               out.println("    <body>");
               out.println("       <h1> já tem uma session " + sessionOptional + "</h2>");
               out.println("       <h1>Login Correto</h2>");
               out.println("       <p><a href='"+ req.getContextPath() + "/home'>Voltar</p>");
               out.println("       <p><a href='"+ req.getContextPath() + "/logout'>logout</p>");
               out.println("    </body>");
               out.println("</html>");
           }

        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(USERNAME.equals(username) && PASSWORD.equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            try (PrintWriter out = resp.getWriter()){
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title> Olá " + session.getAttribute("username") + "</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("       <h1> já tem uma session " + session.getAttribute("username") + "</h2>");
                out.println("       <h1>Login Correto</h2>");
                out.println("       <p><a href='"+ req.getContextPath() + "/index.jsp'>Voltar</p>");
                out.println("       <p><a href='"+ req.getContextPath() + "/logout'>logout</p>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login não autorizado!");
        }

    }
}
