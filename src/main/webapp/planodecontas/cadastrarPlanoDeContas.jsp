<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Plano de Contas</title>
</head>
<body>

    <%
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>

    <h1>Cadastrar Plano de Contas</h1>

    <%
        String sucesso = request.getParameter("sucesso");
        String erro = request.getParameter("erro");

        if ("true".equals(sucesso)) {
    %>
        <p style="color: green;">Plano cadastrado com sucesso!</p>
    <%
        }

        if ("permissao".equals(erro)) {
    %>
        <p style="color: red;">Você não tem permissão para cadastrar plano.</p>
    <%
        }

        if ("sqlerror".equals(erro)) {
    %>
        <p style="color: red;">Erro ao salvar no banco de dados.</p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/cadastrar_plano" method="post">
        <label for="nome">Nome do plano:</label><br>
        <input type="text" id="nome" name="nome" required>
        <br><br>
        <button type="submit">Cadastrar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_planos">Voltar</a>

</body>
</html>