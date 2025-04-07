package br.cadeira.resource;

import br.cadeira.model.Cliente;
import br.cadeira.repository.ClienteRepository;
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

@Path("/cliente")
@Produces(MediaType.TEXT_HTML)
public class ClienteResource {

   @Location("cliente/index.html")
    @Inject Template index;
    @Inject 
    @Location("cliente/form.html")
    Template form;

    @Inject ClienteRepository clienteRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("clientes", clienteRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("cliente", new Cliente());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@BeanParam Cliente cliente) {
        clienteRepo.persist(cliente);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Cliente cliente = clienteRepo.findById(id);
        return form.data("cliente", cliente);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        clienteRepo.deleteById(id);
        return listar();
    }
}
