package br.cefetrj.service;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.PlanoDeContasDAO;
import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;

public class PlanoDeContasService {

    public boolean cadastrarPlano(PlanoDeContas plano, Usuario usuarioLogado) throws SQLException {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.cadastrarPlano(plano);
    }

    public List<PlanoDeContas> listarPlanos(Usuario usuarioLogado) throws SQLException {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.listarPlanos();
    }

    public PlanoDeContas buscarPlanoPorId(Long id, Usuario usuarioLogado) throws SQLException {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.buscarPlanoPorId(id);
    }

    public boolean atualizarPlano(PlanoDeContas plano, Usuario usuarioLogado) throws SQLException {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.atualizarPlano(plano);
    }

    public boolean excluirPlano(Long id, Usuario usuarioLogado) throws SQLException {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.excluirPlano(id);
    }
}