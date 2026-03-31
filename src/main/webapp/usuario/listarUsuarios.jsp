<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Usuario" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
</head>
<body>
    <h1>Lista de Usuários</h1>

    <%
        List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
    %>

    <%
        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
    %>
        <p>Nenhum usuário cadastrado.</p>
    <%
        } else {
    %>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Perfil</th>
                <th>Ações</th>
            </tr>

            <%
                for (Usuario usuario : listaUsuarios) {
            %>
            <tr>
                <td><%= usuario.getId() %></td>
                <td><%= usuario.getNome() %></td>
                <td><%= usuario.getEmail() %></td>
                <td><%= usuario.getPerfil() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/atualizar_usuario?id=<%= usuario.getId() %>">Editar</a>
                    |
                    <a href="<%= request.getContextPath() %>/excluir_usuario?id=<%= usuario.getId() %>"
                       onclick="return confirm('Deseja excluir este usuário?');">Excluir</a>
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
    <a href="<%= request.getContextPath() %>/usuario/cadastrarUsuario.jsp">Cadastrar usuário</a>
    <br><br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para home</a>
</body>
</html>