package br.cadeira.resource;

import br.cadeira.model.Fabricante;
import br.cadeira.repository.FabricanteRepository;
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

@Path("/fabricante")
@Produces(MediaType.TEXT_HTML)
public class FabricanteResource {

    @Inject @Location("fabricante/index.html")
    Template index;

    @Inject @Location("fabricante/form.html")
    Template form;

    @Inject
    FabricanteRepository fabricanteRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("fabricantes", fabricanteRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("fabricante", new Fabricante());
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance salvar(@BeanParam Fabricante fabricante) {
        fabricanteRepo.persist(fabricante);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Fabricante fabricante = fabricanteRepo.findById(id);
        return form.data("fabricante", fabricante);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        fabricanteRepo.deleteById(id);
        return listar();
    }
}
