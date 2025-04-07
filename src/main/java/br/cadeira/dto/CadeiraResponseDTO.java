package br.cadeira.dto;

import br.cadeira.enums.TipoMaterial;

public class CadeiraResponseDTO {
    public Long id;
    public String nome;
    public Double preco;
    public TipoMaterial material;
    public Long fabricanteId;
    public String fabricanteNome;
    public Long categoriaId;
    public String categoriaNome;

    public CadeiraResponseDTO(Long id, String nome, Double preco, TipoMaterial material,
                              Long fabricanteId, String fabricanteNome,
                              Long categoriaId, String categoriaNome) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.material = material;
        this.fabricanteId = fabricanteId;
        this.fabricanteNome = fabricanteNome;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }
}
