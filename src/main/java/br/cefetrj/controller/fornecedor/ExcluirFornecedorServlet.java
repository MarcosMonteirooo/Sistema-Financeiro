package br.cefetrj.controller.fornecedor;

import java.io.IOException;
import java.sql.SQLException;

import br.cefetrj.model.Usuario;
import br.cefetrj.service.FornecedorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/excluir_fornecedor")
public class ExcluirFornecedorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));

        FornecedorService service = new FornecedorService();

        try {
            boolean sucesso = service.excluirFornecedor(id, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/listar_fornecedores");
            } else {
                response.sendRedirect(request.getContextPath() + "/listar_fornecedores");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_fornecedores");
        }
    }
}