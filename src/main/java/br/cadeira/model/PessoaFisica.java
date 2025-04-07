package br.cadeira.model;

import jakarta.persistence.Entity;

@Entity
public class PessoaFisica extends Pessoa {

    public String cpf;
}
