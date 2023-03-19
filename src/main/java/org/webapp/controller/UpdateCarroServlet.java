package org.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.webapp.model.Carro;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/update-cart")
public class UpdateCarroServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("## 01- Entramos no doPostc ##################");
       HttpSession session = req.getSession();
       if(session.getAttribute("carro") != null){
           Carro carro = (Carro)  session.getAttribute("carro");
           this.updateProductos(req, carro);
           this.updateQuantity(req, carro);
       }
       resp.sendRedirect(req.getContextPath() + "/view-cart");
    }

    private void updateProductos(HttpServletRequest request, Carro carro) {
        String[] deleteIds = request.getParameterValues("deleteProducts");
        System.out.println(deleteIds.length);

        if (deleteIds.length > 0) {
            List<String> productoIds = Arrays.asList(deleteIds);
            carro.removeProductos(productoIds);
        }

    }

    private void updateQuantity(HttpServletRequest request, Carro carro) {
        Enumeration<String> enumer = request.getParameterNames();
        while (enumer.hasMoreElements()) {
            String paramName = enumer.nextElement();
            if (paramName.startsWith("cant_")) {
                String id = paramName.substring(5);
                String cantidad = request.getParameter(paramName);
                if (cantidad != null) {
                    carro.updateQuantity(id, Integer.parseInt(cantidad));
                }
            }
        }
    }

}
