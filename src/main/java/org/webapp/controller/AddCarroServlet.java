package org.webapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.webapp.model.Carro;
import org.webapp.model.ItemCarro;
import org.webapp.model.Produto;
import org.webapp.service.ProdutoService;
import org.webapp.service.ProdutoServiceImp;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/add-cart")
public class AddCarroServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        ProdutoService service = new ProdutoServiceImp();
        Optional<Produto> produto = service.getById(id);
        if(produto.isPresent()){
            ItemCarro itemCarro = new ItemCarro(1, produto.get());
            HttpSession session = req.getSession();
            Carro carro;
            if(session.getAttribute("carro") == null){
                carro = new Carro();
                session.setAttribute("carro", carro);
            }else{
                carro= (Carro) session.getAttribute("carro");
            }
            carro.addItemCarro(itemCarro);
        }
        resp.sendRedirect(req.getContextPath() + "/viw-cart");
    }
}
