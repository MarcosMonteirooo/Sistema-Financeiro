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

@WebServlet("/cadastrar")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         //BLOCO DE SEGURANÇA
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            response.sendRedirect("erro.jsp");
            return;
        }

    

        // Cria novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getParameter("nome"));
        novoUsuario.setEmail(request.getParameter("email"));
        novoUsuario.setSenha(request.getParameter("senha"));
        novoUsuario.setPerfil(request.getParameter("perfil"));

        // Chama o service para cadastrar
        UsuarioService service = new UsuarioService();
        try {
            boolean sucesso = service.cadastrarUsuario(novoUsuario, supervisor);
             if (sucesso) {
                response.sendRedirect("usuarios.jsp?sucesso=true");
            } else {
                response.sendRedirect("usuarios.jsp?erro=permissao");
            }
        } catch (Exception e) {
            response.sendRedirect("usuarios.jsp?erro=sqlerror");
        }
        

        // Redireciona conforme sucesso ou falha
       
    }
}
