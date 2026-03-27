package br.cefetrj.model;

public class OperadorFinanceiro extends Usuario {
    
    //Contrutor 
    public OperadorFinanceiro (Long id, String nome, String email, String senha){
        super(id, nome, email, senha, "OPERADOR FINANCEIRO");
    }
}
