<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Fornecedor</title>
</head>
<body>

    <%
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>

    <h1>Cadastrar Fornecedor</h1>

    <%
        String sucesso = request.getParameter("sucesso");
        String erro = request.getParameter("erro");

        if ("true".equals(sucesso)) {
    %>
        <p style="color: green;">Fornecedor cadastrado com sucesso!</p>
    <%
        }

        if ("permissao".equals(erro)) {
    %>
        <p style="color: red;">Você não tem permissão para cadastrar fornecedor.</p>
    <%
        }

        if ("sqlerror".equals(erro)) {
    %>
        <p style="color: red;">Erro ao salvar no banco de dados.</p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/cadastrar_fornecedor" method="post">
        <div>
            <label for="nome">Nome:</label><br>
            <input type="text" id="nome" name="nome" required>
        </div>
        <br>

        <div>
            <label for="cnpj">CNPJ:</label><br>
            <input type="text" id="cnpj" name="cnpj" required>
        </div>
        <br>

        <div>
            <label for="telefone">Telefone:</label><br>
            <input type="text" id="telefone" name="telefone" required>
        </div>
        <br>

        <button type="submit">Cadastrar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para Home</a>

</body>
</html>