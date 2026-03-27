package br.cefetrj.model;

public class Conta {
    //Atributos
    private Long id;
    private String descricao;
    private Double saldo;
    private PlanoDeContas plano;

    //Contrutor
    public Conta (Long id, String descricao, Double saldo, PlanoDeContas plano){
        this.id = id;
        this.descricao = descricao;
        this.saldo = saldo;
        this.plano = plano;
    }

    //Gets

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public PlanoDeContas getPlano() {
        return plano;
    }
}
