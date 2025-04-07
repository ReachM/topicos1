package br.cadeira.resource;

import br.cadeira.model.Categoria;
import br.cadeira.repository.CategoriaRepository;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/categoria")
@Produces(MediaType.TEXT_HTML)
public class CategoriaResource {

    @Location("categoria/index.html")
    @Inject Template index;
    @Inject 
    @Location("categoria/form.html")
    Template form;
    @Inject CategoriaRepository categoriaRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("categorias", categoriaRepo.listAll());
    }

    @GET
    @Path("/nova")
    public TemplateInstance nova() {
        return form.data("categoria", new Categoria());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@BeanParam Categoria categoria) {
        categoriaRepo.persist(categoria);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Categoria c = categoriaRepo.findById(id);
        return form.data("categoria", c);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        categoriaRepo.deleteById(id);
        return listar();
    }
}
