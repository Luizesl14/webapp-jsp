package org.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webapp.service.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/cookie-login", "/login.html"})
public class LoginServlet extends HttpServlet {
    private static final String USERNAME= "admin@gmail.com";
    private static final String PASSWORD= "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LoginServiceImpl auth =  new LoginServiceImpl();
        Optional<String> cookieOptional = auth.getUsername(req);

        if (cookieOptional.isPresent()) {
            resp.setContentType("text/html; charset=UTF-8");
           try (PrintWriter out = resp.getWriter()){
               out.println("<!DOCTYPE html>");
               out.println("<html>");
               out.println("    <head>");
               out.println("       <meta charset=\"UTF-8\">");
               out.println("       <title> Olá " + cookieOptional.get() + "</title>");
               out.println("    </head>");
               out.println("    <body>");
               out.println("       <h1> já tem uma session " + cookieOptional.get() + "</h2>");
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
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.setContentType("text/html");
            try( PrintWriter out = resp.getWriter()){
                out.println("<html>");
                out.println("    <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>login Coreto</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("       <h1>Login Correto</h2>");
                out.println("       <p><a href='"+ req.getContextPath() + "/index.html'>Voltar</p>");
                out.println("       <p><a href='"+ req.getContextPath() + "/logout'>logout</p>");
                out.println("    </body>");
                out.println("</html>");
            }catch (Exception e){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login não autorizado!" + e);
            }
        }

    }
}
