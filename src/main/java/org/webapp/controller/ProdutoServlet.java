package org.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.webapp.model.Produto;
import org.webapp.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoService service = new ProdutoServiceImp();
        List<Produto> produtos = service.listar();

        LoginService auth =  new LoginServiceSessionImpl();
        Optional<String> sessionOptional = auth.getUsername(req);

        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("    <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Listando Produtos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("       <h1>Listando Produtos</h1>");
            out.println("<table>");
            out.println("     <tr>");
            out.println("        <th> id </th>");
            out.println("        <th> Nome </th>");
            out.println("        <th> tipo </th>");

            if(sessionOptional.isPresent()){
                out.println("        <th> preço </th>");
                out.println("        <th> Add </th>");
            }

            out.println("     </tr>");

            produtos.forEach(p -> {
                out.println("<tr>");
                out.println("   <td>" + p.getId() + " </td>");
                out.println("   <td>" + p.getName() + " </td>");
                out.println("   <td>" + p.getTipo() + " </td>");

                if(sessionOptional.isPresent()){
                    out.println("   <td>" + p.getPrice() + " </td>");
                    out.println("   <td><a href=\""
                            + req.getContextPath()
                            + "/add-cart?id="
                            + p.getId() + "\">Add+</a> </td>");
                }
                out.println("</tr>");
            });

            out.println("</table>");
            out.println("       <p><a href='"+ req.getContextPath() + "/index.html'>Voltar</p>");
            out.println("    </body>");
            out.println("</html>");

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado!" + e);
        }
    }


}
