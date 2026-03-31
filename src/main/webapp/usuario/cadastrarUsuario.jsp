<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Usuário</title>
</head>
<body>

    <%
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
    %>

    <h1>Cadastrar Usuário</h1>

    <%
        String sucesso = request.getParameter("sucesso");
        String erro = request.getParameter("erro");

        if ("true".equals(sucesso)) {
    %>
        <p style="color: green;">Usuário cadastrado com sucesso!</p>
    <%
        }

        if ("permissao".equals(erro)) {
    %>
        <p style="color: red;">Você não tem permissão para cadastrar usuário.</p>
    <%
        }

        if ("sqlerror".equals(erro)) {
    %>
        <p style="color: red;">Erro ao salvar no banco de dados.</p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/cadastrar_usuario" method="post">
        <div>
            <label for="nome">Nome:</label><br>
            <input type="text" id="nome" name="nome" required>
        </div>
        <br>

        <div>
            <label for="email">Email:</label><br>
            <input type="email" id="email" name="email" required>
        </div>
        <br>

        <div>
            <label for="senha">Senha:</label><br>
            <input type="password" id="senha" name="senha" required>
        </div>
        <br>

        <div>
            <label for="perfil">Perfil:</label><br>
            <select id="perfil" name="perfil" required>
                <option value="">Selecione</option>
                <option value="SUPERVISOR FINANCEIRO">SUPERVISOR FINANCEIRO</option>
                <option value="OPERADOR FINANCEIRO">OPERADOR FINANCEIRO</option>
            </select>
        </div>
        <br>

        <button type="submit">Cadastrar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para Home</a>

</body>
</html>