package br.edu.ifrs.restinga.dev1.nicholas.cinematico.controller;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.errors.NaoEncontrado;
import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao.UsuarioDAO;
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
public class Usuarios {
    
    @Autowired
    UsuarioDAO usuarioDAO;
    
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
    
    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagar(@PathVariable int id) {
        if(usuarioDAO.existsById(id)) {
            usuarioDAO.deleteById(id);
        } else {
            throw new NaoEncontrado("Usuário não encontrado!");
        }
    }

    @RequestMapping(path="/usuarios/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        final Usuario usuarioBanco = this.buscar(id);
        
        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setLogin(usuario.getLogin());
        usuarioBanco.setSenha(usuario.getSenha());
        usuarioBanco.setDataNascimento(usuario.getDataNascimento());
    }
    
    @RequestMapping(path = "/usuarios/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        Usuario usuarioBanco = usuarioDAO.save(usuario);
        
        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setLogin(usuario.getLogin());
        usuarioBanco.setSenha(usuario.getSenha());
        usuarioBanco.setDataNascimento(usuario.getDataNascimento());
        
        return usuarioBanco;
    }

}
