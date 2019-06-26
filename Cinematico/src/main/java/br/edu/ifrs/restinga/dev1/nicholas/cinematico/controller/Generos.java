package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.RequisicaoInvalida;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.GeneroDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Genero;
import java.util.Optional;
import java.util.List;
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

public class Generos {
    
    @Autowired
    GeneroDAO generoDAO;
    
    @RequestMapping(path = "/generos/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Genero> listarGeneros(){
        return generoDAO.findAll();
    }
    
    @RequestMapping(path = "/generos/{idGenero}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Genero buscarGenero(@PathVariable int idGenero) {
        final Optional<Genero> findById = generoDAO.findById(idGenero);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Gênero não encontrado!");
        }
    }
    
    @RequestMapping(path = "/generos/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Genero cadastrarGenero(@RequestBody Genero genero) {
        Genero generoBanco = generoDAO.save(genero);
        /*
        if(genero.getNomeGenero() == "" || genero.getNomeGenero().isEmpty()){
            throw new RequisicaoInvalida("Nome do Gênero deve ser preenchido!");
        }
        if(genero.getDescricaoGenero() == "" || genero.getDescricaoGenero().isEmpty()){
            throw new RequisicaoInvalida("Descrição do gênero deve ser preenchido");
        }
        if(!generoDAO.findByNomeGenero(genero.getNomeGenero().isEmpty())){
            throw new RequisicaoInvalida("Gênero já cadastrado!");
        }*/
        
        return generoBanco;
    }
    
    @RequestMapping(path="/generos/{idGenero}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarGenero(@PathVariable int idGenero, @RequestBody Genero genero) {
        final Genero generoBanco = this.buscarGenero(idGenero);
        
        /*
         if(genero.getNomeGenero() == ""){
            throw new RequisicaoInvalida("Nome do Gênero deve ser preenchido!");
        }
        if(genero.getDescricaoGenero() == ""){
            throw new RequisicaoInvalida("Descrição do gênero deve ser preenchido");
        }
        */
        
        generoBanco.setNomeGenero(generoBanco.getNomeGenero());
        generoBanco.setDescricaoGenero(genero.getDescricaoGenero());
        
        generoDAO.save(generoBanco);
        
    }
    
    @RequestMapping(path="/generos/{idGenero}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarGenero(@PathVariable int idGenero) {
        if(generoDAO.existsById(idGenero)) {
            generoDAO.deleteById(idGenero);
        } else {
            throw new NaoEncontrado("Gênero não encontrado!");
        }
    }
}
