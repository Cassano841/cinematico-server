package br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.dao;

import br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>{
    
}
