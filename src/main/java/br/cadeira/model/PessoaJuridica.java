package br.cadeira.model;

import jakarta.persistence.Entity;

@Entity
public class PessoaJuridica extends Pessoa {

    public String cnpj;
}
