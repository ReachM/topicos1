package br.cadeira.service;

import java.util.List;

import br.cadeira.model.Categoria;
import br.cadeira.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoriaService {

    private final CategoriaRepository categoriaRepo;

    public CategoriaService(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    public List<Categoria> listar() {
        return categoriaRepo.listAll();
    }

    @Transactional
    public void salvar(Categoria c) {
        categoriaRepo.persist(c);
    }

    @Transactional
    public void deletar(Long id) {
        categoriaRepo.deleteById(id);
    }

    public Categoria buscar(Long id) {
        return categoriaRepo.findById(id);
    }
}
