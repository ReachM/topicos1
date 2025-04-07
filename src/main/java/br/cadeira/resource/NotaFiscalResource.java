package br.cadeira.resource;

import java.util.List;

import br.cadeira.model.NotaFiscal;
import br.cadeira.repository.CadeiraRepository;
import br.cadeira.service.NotaFiscalService;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;

@Path("/nota-fiscal")
public class NotaFiscalResource {

    @Location("nota-fiscal/index.html")
    @Inject Template index;
    @Inject 
    @Location("nota-fiscal/form.html")
    Template form;

    @Inject
    NotaFiscalService service;

    @Inject
    CadeiraRepository cadeiraRepository;

    @GET
    public TemplateInstance listar() {
        List<NotaFiscal> lista = service.listarTodos();
        return index.data("notas", lista);
    }

    @GET
    @Path("/nova")
    public TemplateInstance nova() {
        return form.data("nota", new NotaFiscal()).data("cadeiras", cadeiraRepository.listAll());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance salvar(@BeanParam NotaFiscal nota) {
        service.salvar(nota);
        return listar();
    }

    @GET
    @Path("/deletar/{id}")
    public TemplateInstance deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return listar();
    }
    
}
