package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.FilmeDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Filme;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Usuario;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Filmes {
    
    @Autowired
    FilmeDAO filmeDAO;
    
    @RequestMapping(path = "/filmes/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Filme> listarFilmes(){
        return filmeDAO.findAll();
    }
    
    @RequestMapping(path = "/filmes/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Filme buscarFilme(@PathVariable int id) {
        final Optional<Filme> findById = filmeDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Usuário não encontrado!");
        }
    }
    
    @RequestMapping(path = "/filmes/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Filme cadastrarFilme(@RequestBody Filme filme) {
        Filme filmeBanco = filmeDAO.save(filme);
        
        return filmeBanco;
    }
    
    @RequestMapping(path="/filmes/{idFilme}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarFilme(@PathVariable int idFilme, @RequestBody Filme filme) {
        final Filme filmeBanco = this.buscarFilme(idFilme);
        
        filmeBanco.setNomeFilme(filme.getNomeFilme());
        filmeBanco.setDuracaoFilme(filme.getDuracaoFilme());
        filmeBanco.setFaixaEtaria(filme.getFaixaEtaria());
    }
    
    @RequestMapping(path="/filmes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarFilme(@PathVariable int id) {
        if(filmeDAO.existsById(id)) {
            filmeDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Filme não encontrado!");
        }
    }
}
