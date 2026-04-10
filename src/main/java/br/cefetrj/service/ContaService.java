package br.cefetrj.service;


import java.util.List;

import br.cefetrj.dao.ContaDAO;
import br.cefetrj.model.Conta;
import br.cefetrj.model.Usuario;

public class ContaService {

    public boolean cadastrarConta(Conta conta, Usuario usuarioLogado) {
    ContaDAO dao = new ContaDAO();

    if (usuarioLogado == null) {
        return false;
    }

    return dao.salvar(conta);
}

    public List<Conta> listarContas(Usuario usuarioLogado)  {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return null;
        }

        return dao.listarTodos();
    }

    public Conta buscarContaPorId(Long id, Usuario usuarioLogado) {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return null;
        }

        return dao.buscarPorId(id);
    }

    public boolean atualizarConta(Conta conta, Usuario usuarioLogado) {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return false;
        }

        return dao.atualizar(conta);
    }

    public boolean excluirConta(Long id, Usuario usuarioLogado)  {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return false;
        }

        return dao.deletar(id);
    }
}