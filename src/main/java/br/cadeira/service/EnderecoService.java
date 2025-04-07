package br.cadeira.service;

import java.util.List;

import br.cadeira.model.Endereco;
import br.cadeira.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnderecoService {

    private final EnderecoRepository enderecoRepo;

    public EnderecoService(EnderecoRepository enderecoRepo) {
        this.enderecoRepo = enderecoRepo;
    }

    public List<Endereco> listar() {
        return enderecoRepo.listAll();
    }

    @Transactional
    public void salvar(Endereco endereco) {
        enderecoRepo.persist(endereco);
    }

    @Transactional
    public void deletar(Long id) {
        enderecoRepo.deleteById(id);
    }

    public Endereco buscar(Long id) {
        return enderecoRepo.findById(id);
    }
}
