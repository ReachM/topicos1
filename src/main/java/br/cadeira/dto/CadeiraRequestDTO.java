package br.cadeira.dto;

import br.cadeira.enums.TipoMaterial;

public class CadeiraRequestDTO {
    public String nome;
    public Double preco;
    public TipoMaterial material;
    public Long fabricanteId;
    public Long categoriaId;
}
