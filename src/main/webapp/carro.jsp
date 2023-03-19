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
    <% if(carro.getItens().isEmpty()) { %>

    <p>Você não tem produtos no carro de compras!</p>

    <% } else { %>

    <form name="formcarro" action="<%=request.getContextPath()%>/update-cart" method="post">
        <table>
            <tr>
                <th>id</th>
                <th>nome</th>
                <th>preço</th>
                <th>quantidade</th>
                <th>total</th>
                <th>remover</th>
            </tr>

            <% for(ItemCarro item: carro.getItens()) {%>

            <tr>
                <td><%=item.getProduto().getId() %></td>
                <td><%=item.getProduto().getName() %></td>
                <td><%=item.getProduto().getPrice() %></td>
                <td><input type="text" size="4" name="cant_<%=item.getProduto().getId()%>" value="<%=item.getQuantity()%>" /></td>
                <td><%=item.getTolal() %></td>
                <%--            <td><%=item.getImporte()%></td>--%>
                <td><input type="checkbox" value="<%=item.getProduto().getId()%>" name="deleteProducts" /></td>
            </tr>

            <% }%>

            <tr>
                <td colspan="4" style="text-align: right">Total Carro:</td>
                <td><%=carro.getTotal()%></td>
            </tr>
        </table>
            <a href="javascript:document.formcarro.submit();">Atualizar</a>
    </form>

    <% } %>
    <p><a href="<%=request.getContextPath()%>/produto">Seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/home">voltar</a></p>
</body>
</html>