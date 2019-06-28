package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.RequisicaoInvalida;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.ProdutoraDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Produtora;
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
public class Produtoras {
    
    @Autowired
    ProdutoraDAO produtoraDAO;
    
    @RequestMapping(path = "/produtoras/", method = RequestMethod.GET)
    public Iterable<Produtora> listarProdutoras() {
        return produtoraDAO.findAll();
    }
    
    @RequestMapping(path = "/produtoras/{idProdutora}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Produtora buscarProdutora(@PathVariable int idProdutora) {
        final Optional<Produtora> findById = produtoraDAO.findById(idProdutora);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Produtora não encontrada!");
        }
    }
    
    @RequestMapping(path = "/produtoras/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Produtora cadastrarProdutoras(@RequestBody Produtora produtora) {
        Produtora produtoraBanco = produtoraDAO.save(produtora);
        
        if(produtora.getNomeProdutora().isEmpty() || produtora.getNomeProdutora() == ""){
            throw new RequisicaoInvalida("Nome da produtora deve ser preenchido");
        }
        if(produtora.getLocalProdutora().isEmpty() || produtora.getLocalProdutora() == ""){
            throw new RequisicaoInvalida("Local da produtora deve ser preenchido");
        }
        if(!produtoraDAO.findByNomeProdutora(produtora.getNomeProdutora()).isEmpty()){
            throw new RequisicaoInvalida("Produtora já cadastrada!");
        }
        
        return produtoraBanco;
    }
    
    @RequestMapping(path="/produtoras/{idProdutora}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProdutora(@PathVariable int idProdutora, @RequestBody Produtora produtora) {
        final Produtora produtoraBanco = this.buscarProdutora(idProdutora);
        
        if(produtora.getNomeProdutora().isEmpty() || produtora.getNomeProdutora() == ""){
            throw new RequisicaoInvalida("Nome da produtora deve ser preenchido");
        }
        if(produtora.getLocalProdutora().isEmpty() || produtora.getLocalProdutora() == ""){
            throw new RequisicaoInvalida("Local da produtora deve ser preenchido");
        }
        if(!produtoraDAO.findByNomeProdutora(produtora.getNomeProdutora()).isEmpty()){
            throw new RequisicaoInvalida("Produtora já cadastrada!");
        }
     
        produtoraBanco.setNomeProdutora(produtoraBanco.getNomeProdutora());
        produtoraBanco.setLocalProdutora(produtoraBanco.getLocalProdutora());
        
        produtoraDAO.save(produtoraBanco);
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
