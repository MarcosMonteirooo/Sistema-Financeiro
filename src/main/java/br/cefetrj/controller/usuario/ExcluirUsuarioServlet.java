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

@WebServlet("/excluir_usuario")
public class ExcluirUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));
        UsuarioService service = new UsuarioService();

        try {
            service.excluirUsuario(id, usuarioLogado);
            response.sendRedirect(request.getContextPath() + "/listar_usuarios");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_usuarios");
        }
    }
}
