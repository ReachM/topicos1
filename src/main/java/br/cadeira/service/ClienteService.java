package br.cadeira.service;

import br.cadeira.model.Cliente;
import br.cadeira.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public List<Cliente> listar() {
        return clienteRepo.listAll();
    }

    @Transactional
    public void salvar(Cliente cliente) {
        clienteRepo.persist(cliente);
    }

    @Transactional
    public void deletar(Long id) {
        clienteRepo.deleteById(id);
    }

    public Cliente buscar(Long id) {
        return clienteRepo.findById(id);
    }
}
