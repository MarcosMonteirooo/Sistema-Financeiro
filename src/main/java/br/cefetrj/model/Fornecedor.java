package br.cefetrj.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends GeneriEntity {

    private String nome;
    private String cnpj;
    private String telefone;

    @OneToMany(mappedBy = "fornecedor")
    private List<Conta> contas = new ArrayList<>();

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String cnpj, String telefone) {
        super(id);
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}