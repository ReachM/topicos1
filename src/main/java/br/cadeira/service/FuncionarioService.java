package br.cadeira.service;

import java.util.List;

import br.cadeira.model.Funcionario;
import br.cadeira.repository.FuncionarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepo;

    public FuncionarioService(FuncionarioRepository funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    public List<Funcionario> listar() {
        return funcionarioRepo.listAll();
    }

    @Transactional
    public void salvar(Funcionario funcionario) {
        funcionarioRepo.persist(funcionario);
    }

    @Transactional
    public void deletar(Long id) {
        funcionarioRepo.deleteById(id);
    }

    public Funcionario buscar(Long id) {
        return funcionarioRepo.findById(id);
    }
}
