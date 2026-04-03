package br.cefetrj.model;

import javax.annotation.processing.Generated;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EntidadeGenerica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public EntidadeGenerica(long id){
        this.id= id;
    }

}
