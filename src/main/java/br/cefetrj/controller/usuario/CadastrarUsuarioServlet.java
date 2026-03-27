package br.cefetrj.controller.usuario;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cadastrar")
public class CadastrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Pega o supervisor logado da sessão
        Usuario supervisor = (Usuario) request.getSession().getAttribute("usuarioLogado");

        // Cria novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(request.getParameter("nome"));
        novoUsuario.setEmail(request.getParameter("email"));
        novoUsuario.setSenha(request.getParameter("senha"));
        novoUsuario.setPerfil("CLIENTE"); // por exemplo

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
