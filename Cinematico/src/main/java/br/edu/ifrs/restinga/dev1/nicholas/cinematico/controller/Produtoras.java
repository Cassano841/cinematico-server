package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.ProdutoraDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Produtora;
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
public class Produtoras {
    
    @Autowired
    ProdutoraDAO produtoraDAO;
    
    @RequestMapping(path = "/produtoras/", method = RequestMethod.GET)
    public Iterable<Produtora> listarProdutoras() {
        return produtoraDAO.findAll();
    }
    
    @RequestMapping(path = "/produtoras/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Produtora cadastrarProdutoras(@RequestBody Produtora produtora) {
        produtora.setId(0);
        return produtoraDAO.save(produtora);
    }
    
    
    @RequestMapping(path="/produtoras/{idProdutora}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarProdutora(@PathVariable int idProdutora) {
        if(produtoraDAO.existsById(idProdutora)) {
            produtoraDAO.deleteById(idProdutora);
        } else {
            throw new NaoEncontrado("Produtora não encontrada!");
        }
    }

    
    
    
}
