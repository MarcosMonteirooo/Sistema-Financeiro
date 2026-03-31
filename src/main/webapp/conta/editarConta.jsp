<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.model.Conta" %>
<%@ page import="br.cefetrj.model.Fornecedor" %>
<%@ page import="br.cefetrj.model.PlanoDeContas" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Conta</title>
</head>
<body>

    <%
        Conta conta = (Conta) request.getAttribute("conta");
        List<Fornecedor> listaFornecedores = (List<Fornecedor>) request.getAttribute("listaFornecedores");
        List<PlanoDeContas> listaPlanos = (List<PlanoDeContas>) request.getAttribute("listaPlanos");
    %>

    <h1>Editar Conta</h1>

    <form action="<%= request.getContextPath() %>/atualizar_conta" method="post">
        <input type="hidden" name="id" value="<%= conta.getId() %>">

        <label>Descrição:</label><br>
        <input type="text" name="descricao" value="<%= conta.getDescricao() %>" required>
        <br><br>

        <label>Valor:</label><br>
        <input type="number" step="0.01" name="valor" value="<%= conta.getValor() %>" required>
        <br><br>

        <label>Vencimento:</label><br>
        <input type="date" name="vencimento" value="<%= conta.getVencimento() %>" required>
        <br><br>

        <label>Fornecedor:</label><br>
        <select name="fornecedorId" required>
            <%
                for (Fornecedor fornecedor : listaFornecedores) {
            %>
                <option value="<%= fornecedor.getId() %>"
                    <%= fornecedor.getId().equals(conta.getFornecedorId()) ? "selected" : "" %>>
                    <%= fornecedor.getNome() %>
                </option>
            <%
                }
            %>
        </select>
        <br><br>

        <label>Plano de Contas:</label><br>
        <select name="planoDeContasId" required>
            <%
                for (PlanoDeContas plano : listaPlanos) {
            %>
                <option value="<%= plano.getId() %>"
                    <%= plano.getId().equals(conta.getPlanoDeContasId()) ? "selected" : "" %>>
                    <%= plano.getNome() %>
                </option>
            <%
                }
            %>
        </select>
        <br><br>

        <button type="submit">Salvar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_contas">Voltar</a>

</body>
</html>