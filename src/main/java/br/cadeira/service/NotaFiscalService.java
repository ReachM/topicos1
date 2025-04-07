package br.cadeira.service;

import java.util.List;

import br.cadeira.model.NotaFiscal;
import br.cadeira.repository.NotaFiscalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotaFiscalService {

    @Inject
    NotaFiscalRepository repository;

    public List<NotaFiscal> listarTodos() {
        return repository.listAll();
    }

    public NotaFiscal salvar(NotaFiscal nf) {
        repository.persist(nf);
        return nf;
    }

    public NotaFiscal buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
