package br.cefetrj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario  extends GeneriEntity{

   
    private String nome;
    private String email;
    private String senha;
    private String perfil;

    public Usuario(){

    }

    public Usuario (Long id, String nome, String email, String senha, String perfil){
        super(id);
        this.nome = nome;
        this.email = email; 
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setPerfil(String perfil) {
         this.perfil = perfil;
     }
    public void setSenha(String senha) {
        this.senha = senha;
    }


}
