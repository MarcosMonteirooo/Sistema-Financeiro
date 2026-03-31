<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.PlanoDeContas" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Plano de Contas</title>
</head>
<body>

    <%
        PlanoDeContas plano = (PlanoDeContas) request.getAttribute("plano");
    %>

    <h1>Editar Plano de Contas</h1>

    <form action="<%= request.getContextPath() %>/atualizar_plano" method="post">
        <input type="hidden" name="id" value="<%= plano.getId() %>">

        <label for="nome">Nome do plano:</label><br>
        <input type="text" id="nome" name="nome" value="<%= plano.getNome() %>" required>
        <br><br>

        <button type="submit">Salvar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_planos">Voltar</a>

</body>
</html>