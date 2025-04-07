package br.cadeira.resource;

import br.cadeira.model.Endereco;
import br.cadeira.repository.EnderecoRepository;
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

@Path("/endereco")
@Produces(MediaType.TEXT_HTML)
public class EnderecoResource {

    @Location("endereco/index.html")
    @Inject Template index;
    @Inject 
    @Location("endereco/form.html")
    Template form;

    @Inject EnderecoRepository enderecoRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("enderecos", enderecoRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("endereco", new Endereco());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@BeanParam Endereco endereco) {
        enderecoRepo.persist(endereco);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Endereco endereco = enderecoRepo.findById(id);
        return form.data("endereco", endereco);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        enderecoRepo.deleteById(id);
        return listar();
    }
}
