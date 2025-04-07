package br.cadeira.resource;

import br.cadeira.model.Funcionario;
import br.cadeira.repository.FuncionarioRepository;
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

@Path("/funcionario")
@Produces(MediaType.TEXT_HTML)
public class FuncionarioResource {

    @Location("funcionario/index.html")
    @Inject Template index;
    @Inject 
    @Location("funcionario/form.html")
    Template form;

    @Inject FuncionarioRepository funcionarioRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("funcionarios", funcionarioRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("funcionario", new Funcionario());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@BeanParam Funcionario funcionario) {
        funcionarioRepo.persist(funcionario);
        return listar();
    }

    @GET
    @Path("/editar/{id}")
    public TemplateInstance editar(@PathParam("id") Long id) {
        Funcionario funcionario = funcionarioRepo.findById(id);
        return form.data("funcionario", funcionario);
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        funcionarioRepo.deleteById(id);
        return listar();
    }
}
