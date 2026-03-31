<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Fornecedor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Fornecedores</title>
</head>
<body>

    <h1>Lista de Fornecedores</h1>

    <%
        List<Fornecedor> listaFornecedores = (List<Fornecedor>) request.getAttribute("listaFornecedores");
    %>

    <%
        if (listaFornecedores == null || listaFornecedores.isEmpty()) {
    %>
        <p>Nenhum fornecedor cadastrado.</p>
    <%
        } else {
    %>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Telefone</th>
                <th>Ações</th>
            </tr>

            <%
                for (Fornecedor fornecedor : listaFornecedores) {
            %>
            <tr>
                <td><%= fornecedor.getId() %></td>
                <td><%= fornecedor.getNome() %></td>
                <td><%= fornecedor.getCnpj() %></td>
                <td><%= fornecedor.getTelefone() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/atualizar_fornecedor?id=<%= fornecedor.getId() %>">Editar</a>
                    |
                    <a href="<%= request.getContextPath() %>/excluir_fornecedor?id=<%= fornecedor.getId() %>"
                       onclick="return confirm('Deseja excluir este fornecedor?');">Excluir</a>
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
    <a href="<%= request.getContextPath() %>/fornecedor/cadastrarFornecedor.jsp">Cadastrar fornecedor</a>
    <br><br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para home</a>

</body>
</html>