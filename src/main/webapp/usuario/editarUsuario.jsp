<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuário</title>
</head>
<body>

    <%
        Usuario usuario = (Usuario) request.getAttribute("usuarioEditar");
    %>

    <h1>Editar Usuário</h1>

    <form action="<%= request.getContextPath() %>/atualizar_usuario" method="post">
        <input type="hidden" name="id" value="<%= usuario.getId() %>">

        <label>Nome:</label><br>
        <input type="text" name="nome" value="<%= usuario.getNome() %>" required>
        <br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="<%= usuario.getEmail() %>" required>
        <br><br>

        <label>Senha:</label><br>
        <input type="text" name="senha" value="<%= usuario.getSenha() %>" required>
        <br><br>

        <label>Perfil:</label><br>
        <select name="perfil" required>
            <option value="SUPERVISOR FINANCEIRO"
                <%= "SUPERVISOR FINANCEIRO".equals(usuario.getPerfil()) ? "selected" : "" %>>
                SUPERVISOR FINANCEIRO
            </option>
            <option value="USUARIO COMUM"
                <%= "USUARIO COMUM".equals(usuario.getPerfil()) ? "selected" : "" %>>
                USUARIO COMUM
            </option>
        </select>
        <br><br>

        <button type="submit">Salvar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_usuarios">Voltar</a>

</body>
</html>