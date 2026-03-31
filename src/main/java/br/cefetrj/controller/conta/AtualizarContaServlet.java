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

@WebServlet("/atualizar_conta")
public class AtualizarContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Long id = Long.parseLong(request.getParameter("id"));

        try {
            ContaService contaService = new ContaService();
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            PlanoDeContasDAO planoDAO = new PlanoDeContasDAO();

            Conta conta = contaService.buscarContaPorId(id, usuarioLogado);
            List<Fornecedor> listaFornecedores = fornecedorDAO.listarFornecedores();
            List<PlanoDeContas> listaPlanos = planoDAO.listarPlanos();

            request.setAttribute("conta", conta);
            request.setAttribute("listaFornecedores", listaFornecedores);
            request.setAttribute("listaPlanos", listaPlanos);

            request.getRequestDispatcher("/conta/editarConta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_contas");
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
        String descricao = request.getParameter("descricao");
        BigDecimal valor = new BigDecimal(request.getParameter("valor"));
        Date vencimento = Date.valueOf(request.getParameter("vencimento"));
        Long fornecedorId = Long.parseLong(request.getParameter("fornecedorId"));
        Long planoDeContasId = Long.parseLong(request.getParameter("planoDeContasId"));

        Conta conta = new Conta();
        conta.setId(id);
        conta.setDescricao(descricao);
        conta.setValor(valor);
        conta.setVencimento(vencimento);
        conta.setFornecedorId(fornecedorId);
        conta.setPlanoDeContasId(planoDeContasId);

        ContaService service = new ContaService();

        try {
            service.atualizarConta(conta, usuarioLogado);
            response.sendRedirect(request.getContextPath() + "/listar_contas");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/listar_contas");
        }
    }
}