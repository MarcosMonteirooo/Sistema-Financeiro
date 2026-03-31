<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Fornecedor" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Fornecedor</title>
</head>
<body>

    <%
        Fornecedor fornecedor = (Fornecedor) request.getAttribute("fornecedor");
    %>

    <h1>Editar Fornecedor</h1>

    <form action="<%= request.getContextPath() %>/atualizar_fornecedor" method="post">
        <input type="hidden" name="id" value="<%= fornecedor.getId() %>">

        <div>
            <label for="nome">Nome:</label><br>
            <input type="text" id="nome" name="nome" value="<%= fornecedor.getNome() %>" required>
        </div>
        <br>

        <div>
            <label for="cnpj">CNPJ:</label><br>
            <input type="text" id="cnpj" name="cnpj" value="<%= fornecedor.getCnpj() %>" required>
        </div>
        <br>

        <div>
            <label for="telefone">Telefone:</label><br>
            <input type="text" id="telefone" name="telefone" value="<%= fornecedor.getTelefone() %>" required>
        </div>
        <br>

        <button type="submit">Salvar</button>
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/listar_fornecedores">Voltar</a>

</body>
</html>