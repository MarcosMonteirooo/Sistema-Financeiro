package br.cefetrj.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login_usuario")
public class LoginUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioService service = new UsuarioService();
        Usuario usuario = null;

        try {
            usuario = service.usuarioLogin(email, senha);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Erro no banco de dados: " + e.getMessage());
            return;
        }

        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogado", usuario);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp?erro=true");
        }
    }
}