package br.cefetrj.service;

import java.sql.SQLException;
import java.util.List;

import br.cefetrj.dao.UsuarioDAO;
import br.cefetrj.model.Usuario;

public class UsuarioService {

    public Usuario usuarioLogin(String email, String senha) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario != null && senha.equals(usuario.getSenha())) {
            return usuario;
        }

        return null;
    }

    public boolean cadastrarUsuario(Usuario usuario, Usuario supervisorLogado) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();

        if (supervisorLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(supervisorLogado.getPerfil())) {
            return false;
        }

        if (dao.existeEmail(usuario.getEmail())) {
            return false;
        }

        return dao.cadastrarUsuario(usuario);
    }

    public List<Usuario> listarUsuarios(Usuario supervisorLogado) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();

        if (supervisorLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(supervisorLogado.getPerfil())) {
            return null;
        }

        return dao.listarUsuarios();
    }

    public Usuario buscarUsuarioPorId(Long id, Usuario supervisorLogado) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();

        if (supervisorLogado == null) {
            return null;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(supervisorLogado.getPerfil())) {
            return null;
        }

        return dao.buscarPorId(id);
    }

    public boolean atualizarUsuario(Usuario usuario, Usuario supervisorLogado) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();

        if (supervisorLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(supervisorLogado.getPerfil())) {
            return false;
        }

        return dao.atualizarUsuario(usuario);
    }

    public boolean excluirUsuario(Long id, Usuario supervisorLogado) throws SQLException {
        UsuarioDAO dao = new UsuarioDAO();

        if (supervisorLogado == null) {
            return false;
        }

        if (!"SUPERVISOR FINANCEIRO".equals(supervisorLogado.getPerfil())) {
            return false;
        }

        return dao.excluirUsuario(id);
    }
}