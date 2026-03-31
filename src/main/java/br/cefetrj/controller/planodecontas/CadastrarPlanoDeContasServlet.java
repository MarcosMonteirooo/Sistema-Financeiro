package br.cefetrj.controller.planodecontas;

import java.io.IOException;

import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.PlanoDeContasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cadastrar_plano")
public class CadastrarPlanoDeContasServlet extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/planodecontas/cadastrarPlanoDeContas.jsp?erro=permissao");
            return;
        }

        String nome = request.getParameter("nome");

        PlanoDeContas plano = new PlanoDeContas();
        plano.setNome(nome);

        PlanoDeContasService service = new PlanoDeContasService();

        try {
            boolean sucesso = service.cadastrarPlano(plano, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/planodecontas/cadastrarPlanoDeContas.jsp?sucesso=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/planodecontas/cadastrarPlanoDeContas.jsp?erro=permissao");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/planodecontas/cadastrarPlanoDeContas.jsp?erro=sqlerror");
        }
    }
}