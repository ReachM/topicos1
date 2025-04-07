package br.cadeira.repository;

import java.util.List;

import br.cadeira.model.Cadeira;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CadeiraRepository implements PanacheRepository<Cadeira> {

    public List<Cadeira> findByCor(String cor) {
        return list("cor", cor);
    }
}
