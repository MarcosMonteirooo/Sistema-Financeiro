package br.cefetrj.service;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.FornecedorDAO;
import br.cefetrj.model.Fornecedor;
import br.cefetrj.model.Usuario;

public class FornecedorService {

    public boolean cadastrarFornecedor(Fornecedor fornecedor, Usuario usuarioLogado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.cadastrarFornecedor(fornecedor);
    }

    public List<Fornecedor> listarFornecedores(Usuario usuarioLogado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.listarFornecedores();
    }

    public Fornecedor buscarFornecedorPorId(Long id, Usuario usuarioLogado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.buscarFornecedorPorId(id);
    }

    public boolean atualizarFornecedor(Fornecedor fornecedor, Usuario usuarioLogado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.atualizarFornecedor(fornecedor);
    }

    public boolean excluirFornecedor(Long id, Usuario usuarioLogado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.excluirFornecedor(id);
    }
}