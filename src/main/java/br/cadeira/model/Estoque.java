package br.cadeira.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.ws.rs.FormParam;

@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FormParam("quantidade")
    private int quantidade;

    @FormParam("local")
    private String local;

    // Relacionamentos (se houver), adicione manualmente
    // Exemplo:
    // @ManyToOne
    // @FormParam("cadeiraId")  // se for um ID enviado no form
    // private Cadeira cadeira;

    // Getters e setters obrigatórios
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
