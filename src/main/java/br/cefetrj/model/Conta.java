package br.cefetrj.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class Conta extends EntidadeGenerica{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private String descricao;
    private BigDecimal valor;
    private Date vencimento;

    private Long fornecedorId;
    private Long planoDeContasId;

    private String nomeFornecedor;
    private String nomePlanoDeContas;

    public Conta() {
    }

    public Conta(Long id, String descricao, BigDecimal valor, Date vencimento,
                 Long fornecedorId, Long planoDeContasId) {
        super(id);
        this.descricao = descricao;
        this.valor = valor;
        this.vencimento = vencimento;
        this.fornecedorId = fornecedorId;
        this.planoDeContasId = planoDeContasId;
    }

    public Long getId() {
        return id;
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

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public Long getPlanoDeContasId() {
        return planoDeContasId;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public String getNomePlanoDeContas() {
        return nomePlanoDeContas;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public void setPlanoDeContasId(Long planoDeContasId) {
        this.planoDeContasId = planoDeContasId;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public void setNomePlanoDeContas(String nomePlanoDeContas) {
        this.nomePlanoDeContas = nomePlanoDeContas;
    }
}