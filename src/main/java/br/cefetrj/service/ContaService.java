package br.cefetrj.service;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.ContaDAO;
import br.cefetrj.model.Conta;
import br.cefetrj.model.Usuario;

public class ContaService {

    public boolean cadastrarConta(Conta conta, Usuario usuarioLogado) throws SQLException {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return false;
        }

        return dao.cadastrarConta(conta);
    }

    public List<Conta> listarContas(Usuario usuarioLogado) throws SQLException {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return null;
        }

        return dao.listarContas();
    }

    public Conta buscarContaPorId(Long id, Usuario usuarioLogado) throws SQLException {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return null;
        }

        return dao.buscarContaPorId(id);
    }

    public boolean atualizarConta(Conta conta, Usuario usuarioLogado) throws SQLException {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return false;
        }

        return dao.atualizarConta(conta);
    }

    public boolean excluirConta(Long id, Usuario usuarioLogado) throws SQLException {
        ContaDAO dao = new ContaDAO();

        if (usuarioLogado == null) {
            return false;
        }

        return dao.excluirConta(id);
    }
}