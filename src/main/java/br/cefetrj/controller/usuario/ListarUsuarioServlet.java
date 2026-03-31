package br.cefetrj.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listar_usuarios")
public class ListarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        UsuarioService service = new UsuarioService();

        try {
            List<Usuario> listaUsuarios = service.listarUsuarios(usuarioLogado);

            if (listaUsuarios == null) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Você não tem permissão para listar usuários.");
                return;
            }

            request.setAttribute("listaUsuarios", listaUsuarios);
            request.getRequestDispatcher("/usuario/listarUsuarios.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar usuários.");
        }
    }
}