package br.cefetrj.controller.usuario;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/cadastrar_usuario")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            response.sendRedirect(request.getContextPath() + "/usuario/cadastrarUsuario.jsp?erro=permissao");
            return;
        }

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String perfil = request.getParameter("perfil");

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        novoUsuario.setPerfil(perfil);

        UsuarioService service = new UsuarioService();

        try {
            boolean sucesso = service.cadastrarUsuario(novoUsuario, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/usuario/cadastrarUsuario.jsp?sucesso=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/usuario/cadastrarUsuario.jsp?erro=permissao");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/usuario/cadastrarUsuario.jsp?erro=sqlerror");
        }
    }
}