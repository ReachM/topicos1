package br.cadeira.service;

import java.util.List;

import br.cadeira.model.Pessoa;
import br.cadeira.repository.PessoaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PessoaService {

    private final PessoaRepository pessoaRepo;

    public PessoaService(PessoaRepository pessoaRepo) {
        this.pessoaRepo = pessoaRepo;
    }

    public List<Pessoa> listar() {
        return pessoaRepo.listAll();
    }

    public Pessoa buscar(Long id) {
        return pessoaRepo.findById(id);
    }

    @Transactional
    public void salvar(Pessoa pessoa) {
        pessoaRepo.persist(pessoa);
    }

    @Transactional
    public void deletar(Long id) {
        pessoaRepo.deleteById(id);
    }
}
