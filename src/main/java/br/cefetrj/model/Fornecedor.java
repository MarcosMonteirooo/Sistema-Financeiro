package br.cefetrj.model;

public class Fornecedor {
    //Atributos
    private Long id;
    private String nome;
    private String cnpj;
    private String telefone;

    //Construtor
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
}
