package org.webapp.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.webapp.model.Carro;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
      sce.getServletContext().log("initialization to application");
      servletContext = sce.getServletContext();
      servletContext.setAttribute("menssage", "Algum valor");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("destruction to application");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("initialization to request");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("destruction to request");
        sre.getServletRequest().setAttribute("menssage", "Guardando algum valor");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("initialization to http sesseion");
        Carro carro = new Carro();
        HttpSession session = se.getSession();
        session.setAttribute("carro", carro);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("destruction to http sesseion");
    }
}
