package br.cadeira.resource;

import java.math.BigDecimal;

import br.cadeira.model.Cadeira;
import br.cadeira.model.TipoMaterial;
import br.cadeira.repository.CadeiraRepository;
import br.cadeira.repository.CategoriaRepository;
import br.cadeira.repository.FabricanteRepository;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cadeira")
@Produces(MediaType.TEXT_HTML)
public class CadeiraResource {

    @Inject
    @Location("cadeira/index.html")
    Template index;

    @Inject
    @Location("cadeira/form.html")
    Template form;

    @Inject
    CadeiraRepository cadeiraRepo;

    @Inject
    CategoriaRepository categoriaRepo;

    @Inject
    FabricanteRepository fabricanteRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("cadeiras", cadeiraRepo.listAll());
    }

    @GET
    @Path("/nova")
    public TemplateInstance nova() {
        return form(new Cadeira());
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Cadeira cadeira = cadeiraRepo.findById(id);
        if (cadeira == null) {
            return listar().data("erro", "Cadeira não encontrada.");
        }
        return form(cadeira);
    }

    private TemplateInstance form(Cadeira cadeira) {
        return form.data("cadeira", cadeira)
                  .data("materiais", TipoMaterial.values())
                  .data("fabricantes", fabricanteRepo.listAll())
                  .data("categorias", categoriaRepo.listAll());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance salvar(
            @FormParam("id") Long id,
            @FormParam("nome") String nome,
            @FormParam("preco") Double preco,
            @FormParam("fabricanteId") Long fabricanteId,
            @FormParam("categoriaId") Long categoriaId,
            @FormParam("material") String materialStr) {

        boolean camposInvalidos = nome == null || preco == null || fabricanteId == null || categoriaId == null || materialStr == null;

        if (camposInvalidos) {
            return form(new Cadeira())
                    .data("erro", "Preencha todos os campos obrigatórios.")
                    .data("materiais", TipoMaterial.values())
                    .data("fabricantes", fabricanteRepo.listAll())
                    .data("categorias", categoriaRepo.listAll());
        }

        Cadeira cadeira = (id != null) ? cadeiraRepo.findById(id) : new Cadeira();
        cadeira.setNome(nome);
        cadeira.setPreco(BigDecimal.valueOf(preco));
        cadeira.setFabricante(fabricanteRepo.findById(fabricanteId));
        cadeira.setCategoria(categoriaRepo.findById(categoriaId));
        cadeira.setMaterial(TipoMaterial.valueOf(materialStr));

        cadeiraRepo.persist(cadeira);
        return listar();
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        cadeiraRepo.deleteById(id);
        return listar();
    }
}
