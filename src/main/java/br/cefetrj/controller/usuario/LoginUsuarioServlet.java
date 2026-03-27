package br.cefetrj.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;

import br.cefetrj.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginUsuarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioService service = new UsuarioService();

        boolean loginValido = false;
        try {
            loginValido = service.usuarioLogin(email, senha);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (loginValido) {
            response.sendRedirect("home.jsp");
        } else {
            response.sendRedirect("login.jsp?erro=true");
        }
    }
}
