package br.cadeira.service;

import br.cadeira.model.Estoque;
import br.cadeira.repository.EstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class EstoqueService {

    private final EstoqueRepository estoqueRepo;

    public EstoqueService(EstoqueRepository estoqueRepo) {
        this.estoqueRepo = estoqueRepo;
    }

    public List<Estoque> listar() {
        return estoqueRepo.listAll();
    }

    @Transactional
    public void salvar(Estoque estoque) {
        estoqueRepo.persist(estoque);
    }

    @Transactional
    public void deletar(Long id) {
        estoqueRepo.deleteById(id);
    }

    public Estoque buscar(Long id) {
        return estoqueRepo.findById(id);
    }
}
