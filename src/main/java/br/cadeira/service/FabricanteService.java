package br.cadeira.service;

import java.util.List;

import br.cadeira.model.Fabricante;
import br.cadeira.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FabricanteService {

    private final FabricanteRepository fabricanteRepo;

    public FabricanteService(FabricanteRepository fabricanteRepo) {
        this.fabricanteRepo = fabricanteRepo;
    }

    public List<Fabricante> listar() {
        return fabricanteRepo.listAll();
    }

    @Transactional
    public void salvar(Fabricante f) {
        fabricanteRepo.persist(f);
    }

    @Transactional
    public void deletar(Long id) {
        fabricanteRepo.deleteById(id);
    }

    public Fabricante buscar(Long id) {
        return fabricanteRepo.findById(id);
    }
}
