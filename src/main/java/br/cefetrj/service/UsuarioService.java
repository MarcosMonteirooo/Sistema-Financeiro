package br.cefetrj.service;

import java.sql.SQLException;

import br.cefetrj.dao.UsuarioDAO;
import br.cefetrj.model.Usuario;

public class UsuarioService {

    public boolean usuarioLogin(String email, String senha) throws SQLException {
        
         UsuarioDAO dao = new UsuarioDAO();

        Usuario usuario = dao.buscarPorEmail(email);

        if (usuario != null && senha.equals(usuario.getSenha())) {
            return true;
        }

        return false;
    }


     public boolean cadastrarUsuario(Usuario usuario, Usuario supervisorLogado) throws SQLException {
        
        UsuarioDAO dao = new UsuarioDAO();
        
        if (!"SUPERVISOR".equals(supervisorLogado.getPerfil())) {
            
            return false;
        }

        return dao.cadastrarUsuario(usuario);
    }
    
 }

