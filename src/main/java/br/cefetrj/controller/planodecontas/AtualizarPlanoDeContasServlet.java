package br.cefetrj.controller.planodecontas;

import java.io.IOException;
import java.sql.SQLException;

import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.PlanoDeContasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atualizar_plano")
public class AtualizarPlanoDeContasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));
        PlanoDeContasService service = new PlanoDeContasService();

        try {
            PlanoDeContas plano = service.buscarPlanoPorId(id, usuarioLogado);

            if (plano == null) {
                response.sendRedirect(request.getContextPath() + "/listar_planos");
                return;
            }

            request.setAttribute("plano", plano);
            request.getRequestDispatcher("/planodecontas/editarPlanoDeContas.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_planos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));
        String nome = request.getParameter("nome");

        PlanoDeContas plano = new PlanoDeContas();
        plano.setId(id);
        plano.setNome(nome);

        PlanoDeContasService service = new PlanoDeContasService();

        try {
            service.atualizarPlano(plano, usuarioLogado);
            response.sendRedirect(request.getContextPath() + "/listar_planos");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_planos");
        }
    }
}