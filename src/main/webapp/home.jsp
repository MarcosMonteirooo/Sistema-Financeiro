<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f4f7;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
        }

        .cabecalho {
            background-color: white;
            border: 1px solid #dcdcdc;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 6px;
        }

        .cabecalho h1 {
            margin-top: 0;
            color: #222;
        }

        .cabecalho p {
            margin: 8px 0;
            color: #444;
        }

        .secao {
            background-color: white;
            border: 1px solid #dcdcdc;
            border-radius: 6px;
            padding: 20px;
            margin-bottom: 20px;
        }

        .secao h2 {
            margin-top: 0;
            margin-bottom: 15px;
            color: #333;
            font-size: 20px;
            border-bottom: 1px solid #e5e5e5;
            padding-bottom: 8px;
        }

        .links {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .links a {
            text-decoration: none;
            background-color: #e9ecef;
            color: #222;
            padding: 10px 14px;
            border: 1px solid #cfcfcf;
            border-radius: 4px;
            transition: 0.2s;
        }

        .links a:hover {
            background-color: #dfe3e8;
        }
    </style>
</head>
<body>

<%
    Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

    if (usuarioLogado == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<div class="container">
    <div class="cabecalho">
        <h1>Bem-vindo ao Sistema Financeiro</h1>
        <p><strong>Nome:</strong> <%= usuarioLogado.getNome() %></p>
        <p><strong>Email:</strong> <%= usuarioLogado.getEmail() %></p>
        <p><strong>Perfil:</strong> <%= usuarioLogado.getPerfil() %></p>
    </div>

    <div class="secao">
        <h2>Contas</h2>
        <div class="links">
            <a href="<%= request.getContextPath() %>/listar_contas">Listar contas</a>
        </div>
   </div>

    <div class="secao">
        <h2>Usuários</h2>
        <div class="links">
            <a href="<%= request.getContextPath() %>/listar_usuarios">Listar usuários</a>
        </div>
    </div>

    <div class="secao">
        <h2>Fornecedores</h2>
        <div class="links">
            <a href="<%= request.getContextPath() %>/listar_fornecedores">Listar fornecedores</a>

        </div>
    </div>

   <div class="secao">
    <h2>Plano de Contas</h2>
    <div class="links">
        <a href="<%= request.getContextPath() %>/listar_planos">Listar planos de contas</a>
    </div>

  </div>

</div>

</body>
</html>