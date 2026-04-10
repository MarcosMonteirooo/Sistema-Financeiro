package br.cefetrj.service;


import java.util.List;

import br.cefetrj.dao.PlanoDeContasDAO;
import br.cefetrj.model.PlanoDeContas;
import br.cefetrj.model.Usuario;

public class PlanoDeContasService {

    public boolean cadastrarPlano(PlanoDeContas plano, Usuario usuarioLogado) {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.salvar(plano);
    }

    public List<PlanoDeContas> listarPlanos(Usuario usuarioLogado) {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.listarTodos();
    }

    public PlanoDeContas buscarPlanoPorId(Long id, Usuario usuarioLogado)  {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return null;
        }

        return dao.buscarPorId(id);
    }

    public boolean atualizarPlano(PlanoDeContas plano, Usuario usuarioLogado)  {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.atualizar(plano);
    }

    public boolean excluirPlano(Long id, Usuario usuarioLogado) {
        PlanoDeContasDAO dao = new PlanoDeContasDAO();

        if (usuarioLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(usuarioLogado.getPerfil())) {
            return false;
        }

        return dao.deletar(id);
    }
}