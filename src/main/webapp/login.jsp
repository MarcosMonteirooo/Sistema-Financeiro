<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login do Sistema</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f4f7;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 100%;
            max-width: 400px;
            margin: 80px auto;
            background-color: #ffffff;
            border: 1px solid #dcdcdc;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        h1 {
            text-align: center;
            margin-top: 0;
            margin-bottom: 25px;
            color: #222;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 6px;
            color: #333;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #bbb;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 18px;
            font-size: 14px;
        }

        button {
            width: 100%;
            padding: 11px;
            background-color: #4f6d7a;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 15px;
            cursor: pointer;
        }

        button:hover {
            background-color: #3f5964;
        }

        .erro {
            background-color: #fdeaea;
            color: #a94442;
            border: 1px solid #f5c2c0;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 18px;
            text-align: center;
        }

        .subtexto {
            text-align: center;
            color: #666;
            font-size: 14px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Login</h1>
        <p class="subtexto">Acesse o sistema financeiro</p>

        <%
            String erro = request.getParameter("erro");
            if ("true".equals(erro)) {
        %>
            <div class="erro">Email ou senha inválidos.</div>
        <%
            }
        %>

        <form action="<%= request.getContextPath() %>/login_usuario" method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="senha">Senha</label>
            <input type="password" id="senha" name="senha" required>

            <button type="submit">Entrar</button>
        </form>
    </div>

</body>
</html>