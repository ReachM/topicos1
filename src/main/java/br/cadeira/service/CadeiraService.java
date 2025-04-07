package br.cadeira.service;

import java.util.List;
import java.util.Optional;

import br.cadeira.model.Cadeira;
import br.cadeira.model.Categoria;
import br.cadeira.model.Fabricante;
import br.cadeira.model.TipoMaterial;
import br.cadeira.repository.CadeiraRepository;
import br.cadeira.repository.CategoriaRepository;
import br.cadeira.repository.FabricanteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CadeiraService {

    @Inject
    CadeiraRepository cadeiraRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    FabricanteRepository fabricanteRepository;

    public List<Cadeira> listarTodas() {
        return cadeiraRepository.listAll();
    }

    public Optional<Cadeira> buscarPorId(Long id) {
        return Optional.ofNullable(cadeiraRepository.findById(id));
    }

    @Transactional
    public void salvar(Cadeira cadeira, Long categoriaId, Long fabricanteId, String materialStr) {
        Categoria categoria = categoriaRepository.findById(categoriaId);
        Fabricante fabricante = fabricanteRepository.findById(fabricanteId);
        TipoMaterial material = TipoMaterial.valueOf(materialStr);

        cadeira.setCategoria(categoria);
        cadeira.setFabricante(fabricante);
        cadeira.setMaterial(material);

        cadeiraRepository.persist(cadeira);
    }

    @Transactional
    public void atualizar(Long id, Cadeira dadosAtualizados, Long categoriaId, Long fabricanteId, String materialStr) {
        Cadeira cadeira = cadeiraRepository.findById(id);
        if (cadeira != null) {
            cadeira.setNome(dadosAtualizados.getNome());
            cadeira.setPreco(dadosAtualizados.getPreco());
            cadeira.setCategoria(categoriaRepository.findById(categoriaId));
            cadeira.setFabricante(fabricanteRepository.findById(fabricanteId));
            cadeira.setMaterial(TipoMaterial.valueOf(materialStr));
        }
    }

    @Transactional
    public void deletar(Long id) {
        cadeiraRepository.deleteById(id);
    }
}
