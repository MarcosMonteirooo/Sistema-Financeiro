<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.PlanoDeContas" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Planos de Contas</title>
</head>
<body>

    <h1>Lista de Planos de Contas</h1>

    <%
        List<PlanoDeContas> listaPlanos = (List<PlanoDeContas>) request.getAttribute("listaPlanos");
    %>

    <%
        if (listaPlanos == null || listaPlanos.isEmpty()) {
    %>
        <p>Nenhum plano de contas cadastrado.</p>
    <%
        } else {
    %>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Ações</th>
            </tr>

            <%
                for (PlanoDeContas plano : listaPlanos) {
            %>
            <tr>
                <td><%= plano.getId() %></td>
                <td><%= plano.getNome() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/atualizar_plano?id=<%= plano.getId() %>">Editar</a>
                    |
                    <a href="<%= request.getContextPath() %>/excluir_plano?id=<%= plano.getId() %>"
                       onclick="return confirm('Deseja excluir este plano?');">Excluir</a>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        }
    %>

    <br>
    <a href="<%= request.getContextPath() %>/planodecontas/cadastrarPlanoDeContas.jsp">Cadastrar plano de contas</a>
    <br><br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para home</a>

</body>
</html>