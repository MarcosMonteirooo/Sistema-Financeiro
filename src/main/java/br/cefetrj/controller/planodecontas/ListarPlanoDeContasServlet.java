package br.cefetrj.controller.planodecontas;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.PlanoDeContasService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listar_planos")
public class ListarPlanoDeContasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        PlanoDeContasService service = new PlanoDeContasService();

        try {
            List<PlanoDeContas> listaPlanos = service.listarPlanos(usuarioLogado);

            if (listaPlanos == null) {
                response.sendRedirect(request.getContextPath() + "/home.jsp");
                return;
            }

            request.setAttribute("listaPlanos", listaPlanos);
            request.getRequestDispatcher("/planodecontas/listarPlanosDeContas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
    }
}