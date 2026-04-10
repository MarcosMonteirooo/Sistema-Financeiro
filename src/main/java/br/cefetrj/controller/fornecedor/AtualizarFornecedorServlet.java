package br.cefetrj.controller.fornecedor;

import java.io.IOException;
import java.sql.SQLException;

import br.cefetrj.model.Fornecedor;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.FornecedorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/atualizar_fornecedor")
public class AtualizarFornecedorServlet extends HttpServlet {

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
            Fornecedor fornecedor = service.buscarFornecedorPorId(id, usuarioLogado);

            if (fornecedor == null) {
                response.sendRedirect(request.getContextPath() + "/listar_fornecedores");
                return;
            }

            request.setAttribute("fornecedor", fornecedor);
            request.getRequestDispatcher("/fornecedor/editarFornecedor.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_fornecedores");
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
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(id);
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        fornecedor.setTelefone(telefone);

        FornecedorService service = new FornecedorService();

        try {
            boolean sucesso = service.atualizarFornecedor(fornecedor, usuarioLogado);

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