<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Conta" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Contas</title>
</head>
<body>

    <h1>Lista de Contas</h1>

    <%
        List<Conta> listaContas = (List<Conta>) request.getAttribute("listaContas");
    %>

    <%
        if (listaContas == null || listaContas.isEmpty()) {
    %>
        <p>Nenhuma conta cadastrada.</p>
    <%
        } else {
    %>
        <table border="1" cellpadding="8">
            <tr>
                <th>ID</th>
                <th>Fornecedor</th>
                <th>Descrição</th>
                <th>Plano de Contas</th>
                <th>Valor</th>
                <th>Vencimento</th>
                <th>Ações</th>
            </tr>

            <%
                for (Conta conta : listaContas) {
            %>
            <tr>
                <td><%= conta.getId() %></td>
                <td><%= conta.getFornecedor() != null ? conta.getFornecedor().getNome() : "" %></td>
                <td><%= conta.getDescricao() %></td>
                <td><%= conta.getPlanoDeContas() != null ? conta.getPlanoDeContas().getNome() : "" %></td>
                <td>R$ <%= conta.getValor() %></td>
                <td><%= conta.getVencimento() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/atualizar_conta?id=<%= conta.getId() %>">Editar</a>
                    |
                    <a href="<%= request.getContextPath() %>/excluir_conta?id=<%= conta.getId() %>"
                       onclick="return confirm('Deseja excluir esta conta?');">Excluir</a>
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
    <a href="<%= request.getContextPath() %>/cadastrar_conta">Cadastrar conta</a>
    <br><br>
    <a href="<%= request.getContextPath() %>/home.jsp">Voltar para home</a>

</body>
</html>