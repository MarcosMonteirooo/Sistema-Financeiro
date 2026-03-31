package br.cefetrj.controller.conta;

import java.io.IOException;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.ContaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/excluir_conta")
public class ExcluirContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));
        ContaService service = new ContaService();

        try {
            service.excluirConta(id, usuarioLogado);
            response.sendRedirect(request.getContextPath() + "/listar_contas");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_contas");
        }
    }
}