package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.RequisicaoInvalida;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.FilmeDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.GeneroDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Filme;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Filmes {
    
    @Autowired
    FilmeDAO filmeDAO;
    
    @Autowired
    GeneroDAO generoDAO;
    
    /*************/
    /* PESQUISAS */
    /*************/
    
    @RequestMapping(path = "/filmes/pesquisar/nomeFilme", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Filme> pesquisaNome(@RequestParam(required = false) String contem){ 
        if(contem != null){
            return filmeDAO.findByNomeFilmeContaining(contem);
        } else {
            throw new RequisicaoInvalida("Nome do filme não encontrado!");
        }
    }
    
    /***************/
    /* CRUD FILMES */
    /***************/
    
    @RequestMapping(path = "/filmes/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Filme> listarFilmes(){
        return filmeDAO.findAll();
    }
    
    @RequestMapping(path = "/filmes/{idFilme}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Filme buscarFilme(@PathVariable int idFilme) {
        final Optional<Filme> findById = filmeDAO.findById(idFilme);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Filme não encontrado!");
        }
    }
    
    @RequestMapping(path = "/filmes/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Filme cadastrarFilme(@RequestBody Filme filme) {
        Filme filmeBanco = filmeDAO.save(filme);
        /*
        if(filmeBanco.getNomeFilme() == null || filmeBanco.getNomeFilme() == ""){
            throw new RequisicaoInvalida("Nome do filme deve ser preenchido");
        }
        if(filmeBanco.getDuracaoFilme() <= 0){ //ARRUMAR ESTA PARTE
            throw new RequisicaoInvalida("Duração do filme deve ser preenchido e maior que 0");
        }
        if(filmeBanco.getFaixaEtaria() <= 0){ //ARRUMAR ESTA PARTE
            throw new RequisicaoInvalida("Faixa etária deve ser maior que 0");
        }
        if(filmeBanco.getAvaliacao()<= 0 || filmeBanco.getAvaliacao()>5){
            throw new RequisicaoInvalida("Avaliação deve ser entre 1 e 5");
        }
        */
        
        return filmeBanco;
    }
    
    @RequestMapping(path="/filmes/{idFilme}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarFilme(@PathVariable int idFilme, @RequestBody Filme filme) {
        final Filme filmeBanco = this.buscarFilme(idFilme);
        
        /*
        if(filmeBanco.getNomeFilme() == null || filmeBanco.getNomeFilme() == ""){
            throw new RequisicaoInvalida("Nome do filme deve ser preenchido");
        }
        if(filmeBanco.getDuracaoFilme() <= 0){ //ARRUMAR ESTA PARTE
            throw new RequisicaoInvalida("Duração do filme deve ser preenchido e maior que 0");
        }
        if(filmeBanco.getFaixaEtaria() <= 0){
            throw new RequisicaoInvalida("Faixa etária deve ser maior que 0");
        }
        if(filmeBanco.getAvaliacao() <= 0 || filmeBanco.getAvaliacao() >5){
            throw new RequisicaoInvalida("Avaliação deve ser entre 1 e 5");
        }
        */
        filmeBanco.setNomeFilme(filme.getNomeFilme());
        filmeBanco.setDuracaoFilme(filme.getDuracaoFilme());
        filmeBanco.setFaixaEtaria(filme.getFaixaEtaria());
        filmeBanco.setAvaliacao(filme.getAvaliacao());
        filmeBanco.setGenero(filme.getGenero());
        filmeBanco.setProdutora(filme.getProdutora());
        
        filmeDAO.save(filmeBanco);
    }
    
    @RequestMapping(path="/filmes/{idFilme}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarFilme(@PathVariable int idFilme) {
        if(filmeDAO.existsById(idFilme)) {
            filmeDAO.deleteById(idFilme);
        } else {
            throw new NaoEncontrado("Filme não encontrado!");
        }
    }
 
}
