package br.cefetrj.service;


import java.util.List;

import br.cefetrj.dao.FornecedorDAO;
import br.cefetrj.model.Fornecedor;
import br.cefetrj.model.Usuario;

public class FornecedorService {

    public boolean cadastrarFornecedor(Fornecedor fornecedor, Usuario usuarioLogado) {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.salvar(fornecedor);
    }

    public List<Fornecedor> listarFornecedores(Usuario usuarioLogado) {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.listarTodos();
    }

    public Fornecedor buscarFornecedorPorId(Long id, Usuario usuarioLogado)  {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.buscarPorId(id);
    }

    public boolean atualizarFornecedor(Fornecedor fornecedor, Usuario usuarioLogado)  {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.atualizar(fornecedor);
    }

    public boolean excluirFornecedor(Long id, Usuario usuarioLogado)  {
        FornecedorDAO dao = new FornecedorDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.deletar(id);
    }
}