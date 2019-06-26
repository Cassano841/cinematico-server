package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.RequisicaoInvalida;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.UsuarioDAO;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Usuario;
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
public class Usuarios {
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
    /*************/
    /* PESQUISAS */
    /*************/
    
    @RequestMapping(path = "/usuarios/pesquisar/nome", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> pesquisaNome(@RequestParam(required = false) String contem){ 
        if(contem != null){
            return usuarioDAO.findByNomeContaining(contem);
        } else {
            throw new RequisicaoInvalida("Nome não encontrado!");
        }
    }
    
    @RequestMapping(path = "/usuarios/pesquisar/login", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> pesquisaLogin(@RequestParam(required = false) String contem){ 
        if(contem != null){
            return usuarioDAO.findByNomeContaining(contem);
        } else {
            throw new RequisicaoInvalida("Login não encontrado!");
        }
    }
    
    /****************/
    /* CRUD USUÁRIO */
    /****************/
    
    @RequestMapping(path = "/usuarios/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Usuario> listar(){
        return usuarioDAO.findAll();
    }
    
    @RequestMapping(path = "/usuarios/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Usuario buscar(@PathVariable int id) {
        final Optional<Usuario> findById = usuarioDAO.findById(id);
        if(findById.isPresent()) {
            return findById.get();
        } else {
            throw new NaoEncontrado("Usuário não encontrado!");
        }
    }
    
    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioBanco = usuarioDAO.save(usuario);
        
        if(usuarioBanco.getNome() == null || usuarioBanco.getNome() == ""){
            throw new RequisicaoInvalida("Nome deve ser preenchido");
        }
        if(usuarioBanco.getLogin() == null || usuarioBanco.getLogin() == ""){
            throw new RequisicaoInvalida("Login deve ser preenchido");
        }
        if(usuarioBanco.getSenha() == null || usuarioBanco.getSenha() == ""){
            throw new RequisicaoInvalida("Senha deve ser preenchido");
        }
        
        return usuarioBanco;
    }
    
    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        final Usuario usuarioBanco = this.buscar(id);
        
        if(usuarioBanco.getNome() == null || usuarioBanco.getNome() == ""){
            throw new RequisicaoInvalida("Nome deve ser preenchido");
        }
        if(usuarioBanco.getLogin() == null || usuarioBanco.getLogin() == ""){
            throw new RequisicaoInvalida("Login deve ser preenchido");
        }
        if(usuarioBanco.getSenha() == null || usuarioBanco.getSenha() == ""){
            throw new RequisicaoInvalida("Senha deve ser preenchido");
        }
        
        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setLogin(usuario.getLogin());
        usuarioBanco.setSenha(usuario.getSenha());
        
        usuarioDAO.save(usuarioBanco);
    }
    
    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagarUsuario(@PathVariable int id) {
        if(usuarioDAO.existsById(id)) {
            usuarioDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Usuário não encontrado!");
        }
    }    
   
}

