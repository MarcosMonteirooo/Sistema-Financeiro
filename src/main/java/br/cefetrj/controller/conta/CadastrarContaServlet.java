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
    private static final long serialVersionUID = 1L;

    private void carregarListas(HttpServletRequest request) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        PlanoDeContasDAO planoDAO = new PlanoDeContasDAO();

        List<Fornecedor> listaFornecedores = fornecedorDAO.listarTodos();
        List<PlanoDeContas> listaPlanos = planoDAO.listarTodos();

        request.setAttribute("listaFornecedores", listaFornecedores);
        request.setAttribute("listaPlanos", listaPlanos);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            carregarListas(request);
            request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
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

        try {
            String descricao = request.getParameter("descricao");
            String valorParam = request.getParameter("valor");
            String vencimentoParam = request.getParameter("vencimento");
            String fornecedorIdParam = request.getParameter("fornecedorId");
            String planoDeContasIdParam = request.getParameter("planoDeContasId");

            if (descricao == null || descricao.trim().isEmpty() ||
                valorParam == null || valorParam.trim().isEmpty() ||
                vencimentoParam == null || vencimentoParam.trim().isEmpty() ||
                fornecedorIdParam == null || fornecedorIdParam.trim().isEmpty() ||
                planoDeContasIdParam == null || planoDeContasIdParam.trim().isEmpty()) {

                request.setAttribute("erro", "Preencha todos os campos obrigatórios.");
                carregarListas(request);
                request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
                return;
            }

            BigDecimal valor = new BigDecimal(valorParam);
            Date vencimento = Date.valueOf(vencimentoParam);
            Long fornecedorId = Long.parseLong(fornecedorIdParam);
            Long planoDeContasId = Long.parseLong(planoDeContasIdParam);

            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            PlanoDeContasDAO planoDAO = new PlanoDeContasDAO();

            Fornecedor fornecedor = fornecedorDAO.buscarPorId(fornecedorId);
            PlanoDeContas planoDeContas = planoDAO.buscarPorId(planoDeContasId);

            if (fornecedor == null) {
                request.setAttribute("erro", "Fornecedor não encontrado.");
                carregarListas(request);
                request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
                return;
            }

            if (planoDeContas == null) {
                request.setAttribute("erro", "Plano de contas não encontrado.");
                carregarListas(request);
                request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
                return;
            }

            Conta conta = new Conta();
            conta.setDescricao(descricao);
            conta.setValor(valor);
            conta.setVencimento(vencimento);
            conta.setFornecedor(fornecedor);
            conta.setPlanoDeContas(planoDeContas);

            ContaService service = new ContaService();
            boolean sucesso = service.cadastrarConta(conta, usuarioLogado);

            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/listar_contas");
            } else {
                request.setAttribute("erro", "Não foi possível cadastrar a conta.");
                carregarListas(request);
                request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Fornecedor, plano de contas ou valor em formato inválido.");
            carregarListas(request);
            request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", "Data de vencimento inválida. Use o formato yyyy-MM-dd.");
            carregarListas(request);
            request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar conta: " + e.getMessage());
            carregarListas(request);
            request.getRequestDispatcher("/conta/cadastrarConta.jsp").forward(request, response);
        }
    }
}