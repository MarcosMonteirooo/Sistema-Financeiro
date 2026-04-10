package br.cefetrj.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "plano_de_conta")
public class PlanoDeContas extends GeneriEntity {

    private String nome;

    @OneToMany(mappedBy = "planoDeContas")
    private List<Conta> contas = new ArrayList<>();

    public PlanoDeContas() {
    }

    public PlanoDeContas(Long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}