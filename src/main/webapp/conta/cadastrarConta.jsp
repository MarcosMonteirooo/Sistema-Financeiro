<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Fornecedor" %>
<%@ page import="br.cefetrj.model.PlanoDeContas" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastrar Conta</title>
</head>
<body>

    <h1>Cadastrar Conta</h1>

    <%
        List<Fornecedor> listaFornecedores = (List<Fornecedor>) request.getAttribute("listaFornecedores");
        List<PlanoDeContas> listaPlanos = (List<PlanoDeContas>) request.getAttribute("listaPlanos");
    %>

    <form action="<%= request.getContextPath() %>/cadastrar_conta" method="post">
        <label>Descrição:</label><br>
        <input type="text" name="descricao" required>
        <br><br>

        <label>Valor:</label><br>
        <input type="number" step="0.01" name="valor" required>
        <br><br>

        <label>Vencimento:</label><br>
        <input type="date" name="vencimento" required>
        <br><br>

        <label>Fornecedor:</label><br>
        <select name="fornecedorId" required>
            <option value="">Selecione</option>
            <%
                if (listaFornecedores != null) {
                    for (Fornecedor fornecedor : listaFornecedores) {
            %>
                <option value="<%= fornecedor.getId() %>"><%= fornecedor.getNome() %></option>
            <%
                    }
                }
            %>
        </select>
        <br><br>

        <label>Plano de Contas:</label><br>
        <select name="planoDeContasId" required>
            <option value="">Selecione</option>
            <%
                if (listaPlanos != null) {
                    for (PlanoDeContas plano : listaPlanos) {
            %>
                <option value="<%= plano.getId() %>"><%= plano.getNome() %></option>
            <%
                    }
                }
            %>
        </select>
        <br><br>

        <button type="submit">Cadastrar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_contas">Voltar</a>

</body>
</html>