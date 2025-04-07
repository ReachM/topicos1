package br.cadeira.resource;

import br.cadeira.model.Estoque;
import br.cadeira.repository.EstoqueRepository;
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

@Path("/estoque")
@Produces(MediaType.TEXT_HTML)
public class EstoqueResource {

    @Location("estoque/index.html")
    @Inject Template index;
    @Inject 
    @Location("estoque/form.html")
    Template form;

    @Inject EstoqueRepository estoqueRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("estoques", estoqueRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("estoque", new Estoque());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@BeanParam Estoque estoque) {
        estoqueRepo.persist(estoque);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Estoque estoque = estoqueRepo.findById(id);
        return form.data("estoque", estoque);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        estoqueRepo.deleteById(id);
        return listar();
    }
}
