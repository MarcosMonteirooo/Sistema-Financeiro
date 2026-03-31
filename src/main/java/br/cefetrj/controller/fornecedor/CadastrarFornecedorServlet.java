package br.cefetrj.controller.fornecedor;

import java.io.IOException;

import br.cefetrj.model.Fornecedor;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.FornecedorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cadastrar_fornecedor")
public class CadastrarFornecedorServlet extends HttpServlet {

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
            response.sendRedirect(request.getContextPath() + "/fornecedor/cadastrarFornecedor.jsp?erro=permissao");
            return;
        }

        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(nome);
        fornecedor.setCnpj(cnpj);
        fornecedor.setTelefone(telefone);

        FornecedorService service = new FornecedorService();

        try {
            boolean sucesso = service.cadastrarFornecedor(fornecedor, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/fornecedor/cadastrarFornecedor.jsp?sucesso=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/fornecedor/cadastrarFornecedor.jsp?erro=permissao");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/fornecedor/cadastrarFornecedor.jsp?erro=sqlerror");
        }
    }
}