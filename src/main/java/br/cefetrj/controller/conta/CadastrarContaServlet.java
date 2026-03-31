package br.cefetrj.controller.conta;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import br.cefetrj.dao.FornecedorDAO;
import br.cefetrj.dao.PlanoDeContasDAO;
import br.cefetrj.model.Conta;
import br.cefetrj.model.Fornecedor;
import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;
import br.cefetrj.service.ContaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cadastrar_conta")
public class CadastrarContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            PlanoDeContasDAO planoDAO = new PlanoDeContasDAO();

            List<Fornecedor> listaFornecedores = fornecedorDAO.listarFornecedores();
            List<PlanoDeContas> listaPlanos = planoDAO.listarPlanos();

            request.setAttribute("listaFornecedores", listaFornecedores);
            request.setAttribute("listaPlanos", listaPlanos);

            request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/home.jsp");
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

        String descricao = request.getParameter("descricao");
        BigDecimal valor = new BigDecimal(request.getParameter("valor"));
        Date vencimento = Date.valueOf(request.getParameter("vencimento"));
        Long fornecedorId = Long.parseLong(request.getParameter("fornecedorId"));
        Long planoDeContasId = Long.parseLong(request.getParameter("planoDeContasId"));

        Conta conta = new Conta();
        conta.setDescricao(descricao);
        conta.setValor(valor);
        conta.setVencimento(vencimento);
        conta.setFornecedorId(fornecedorId);
        conta.setPlanoDeContasId(planoDeContasId);

        ContaService service = new ContaService();

        try {
            boolean sucesso = service.cadastrarConta(conta, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/listar_contas");
            } else {
                response.sendRedirect(request.getContextPath() + "/cadastrar_conta");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/cadastrar_conta");
        }
    }
}