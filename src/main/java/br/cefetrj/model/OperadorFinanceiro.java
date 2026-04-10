package br.cefetrj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class OperadorFinanceiro extends Usuario {
    
    //Contrutor 
    public OperadorFinanceiro (Long id, String nome, String email, String senha){
        super(id, nome, email, senha, "OPERADOR FINANCEIRO");
    }
}
