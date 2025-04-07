package br.cadeira.resource;

import br.cadeira.model.Pessoa;
import br.cadeira.model.PessoaFisica;
import br.cadeira.model.PessoaJuridica;
import br.cadeira.repository.PessoaRepository;
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

@Path("/pessoa")
@Produces(MediaType.TEXT_HTML)
public class PessoaResource {

    @Location("pessoa/index.html")
    @Inject Template index;
    @Inject 
    @Location("pessoa/form.html")
    Template form;
    @Inject PessoaRepository pessoaRepo;

    @GET
    public TemplateInstance listar() {
        return index.data("pessoas", pessoaRepo.listAll());
    }

    @GET
    @Path("/novo")
    public TemplateInstance novo() {
        return form.data("pessoa", new PessoaFisica()); // padrão
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Transactional
    public TemplateInstance salvar(@FormParam("id") Long id,
                                   @FormParam("nome") String nome,
                                   @FormParam("email") String email,
                                   @FormParam("tipo") String tipo,
                                   @FormParam("cpf") String cpf,
                                   @FormParam("cnpj") String cnpj) {

        Pessoa pessoa;
        if ("fisica".equals(tipo)) {
            PessoaFisica pf = new PessoaFisica();
            pf.cpf = cpf;
            pessoa = pf;
        } else {
            PessoaJuridica pj = new PessoaJuridica();
            pj.cnpj = cnpj;
            pessoa = pj;
        }

        pessoa.nome = nome;
        pessoa.email = email;

        pessoaRepo.persist(pessoa);
        return listar();
    }

    @GET
    @Path("/deletar/{id}")
    @Transactional
    public TemplateInstance deletar(@PathParam("id") Long id) {
        pessoaRepo.deleteById(id);
        return listar();
    }
}
