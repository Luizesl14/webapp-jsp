<%@page contentType="text/html" pageEncoding="UTF-8" import="org.webapp.model.*" %>
<%
    Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
</head>
<body>
    <% if(carro==null || carro.getItems().isEmpty()) { %>

    <p>Você não tem produtos no carro de compras!</p>

    <% } else { %>

    <table>
        <tr>
            <th>id</th>
            <th>nome</th>
            <th>preço</th>
            <th>quantidade</th>
            <th>total</th>
        </tr>

        <% for(ItemCarro item: carro.getItems()) {%>

        <tr>
            <td><%=item.getProduto().getId() %></td>
            <td><%=item.getProduto().getName() %></td>
            <td><%=item.getProduto().getPrice() %></td>
            <td><%=item.getQuantity() %></td>
            <td><%=item.getTolal() %></td>
        </tr>

        <% }%>

        <tr>
            <td colspan="4" style="text-align: right">Total Carro:</td>
            <td><%=carro.getTotal()%></td>
        </tr>
    </table>

    <% } %>

    <p><a href="<%=request.getContextPath()%>/produto">Seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">voltar</a></p>
</body>
</html>