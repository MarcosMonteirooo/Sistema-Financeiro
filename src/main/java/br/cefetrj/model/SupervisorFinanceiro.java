package br.cefetrj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class SupervisorFinanceiro extends Usuario{
    
    //Contrutor 
    public SupervisorFinanceiro (Long id, String nome, String email, String senha){
        super(id, nome, email, senha, "SUPERVISOR FINANCEIRO");
    }
}
