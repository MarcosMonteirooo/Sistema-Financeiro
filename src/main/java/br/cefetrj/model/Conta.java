package br.cefetrj.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta extends GeneriEntity {

    private String descricao;
    private BigDecimal valor;
    private Date vencimento;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "plano_de_contas_id")
    private PlanoDeContas planoDeContas;

    public Conta() {
    }

    public Conta(Long id, String descricao, BigDecimal valor, Date vencimento,
                 Fornecedor fornecedor, PlanoDeContas planoDeContas) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
        this.fornecedor = fornecedor;
        this.planoDeContas = planoDeContas;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public PlanoDeContas getPlanoDeContas() {
        return planoDeContas;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setPlanoDeContas(PlanoDeContas planoDeContas) {
        this.planoDeContas = planoDeContas;
    }
}