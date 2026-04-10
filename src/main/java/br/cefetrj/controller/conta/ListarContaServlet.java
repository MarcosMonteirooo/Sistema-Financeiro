package br.cefetrj.controller.conta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.cefetrj.model.Conta;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.ContaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listar_contas")
public class ListarContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        ContaService service = new ContaService();

        try {
            List<Conta> listaContas = service.listarContas(usuarioLogado);
            request.setAttribute("listaContas", listaContas);
            request.getRequestDispatcher("/conta/listarContas.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        }
    }
}