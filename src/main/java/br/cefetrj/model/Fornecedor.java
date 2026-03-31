package br.cefetrj.model;

public class Fornecedor {
    //Atributos
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;

    //Construtor

    public Fornecedor (){

    }

    public Fornecedor (Long id, String nome, String cnpj, String telefone){
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    //Gets

    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    //Sets

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
