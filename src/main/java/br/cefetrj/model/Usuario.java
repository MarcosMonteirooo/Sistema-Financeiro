package br.cefetrj.model;

public class Usuario {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String perfil;

    public Usuario(){

    }

    public Usuario (Long id, String nome, String email, String senha, String perfil){
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
    return id;
}
}
